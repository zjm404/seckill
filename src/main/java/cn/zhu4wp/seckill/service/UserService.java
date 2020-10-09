package cn.zhu4wp.seckill.service;

import cn.zhu4wp.seckill.base.exception.GlobalException;
import cn.zhu4wp.seckill.base.result.CodeMsg;
import cn.zhu4wp.seckill.mybatis.dao.UserMapper;
import cn.zhu4wp.seckill.entity.User;
import cn.zhu4wp.seckill.redis.RedisService;
import cn.zhu4wp.seckill.redis.UserKey;
import cn.zhu4wp.seckill.util.CookieUtil;
import cn.zhu4wp.seckill.util.MD5Util;
import cn.zhu4wp.seckill.util.UUIDUtil;
import cn.zhu4wp.seckill.vo.LoginVO;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;

@Service("userService")
public class UserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private RedisService redisService;

    private Random random = new Random();

    public String  login (HttpServletResponse response, LoginVO loginVO){
        //登录信息不可为空
        if(loginVO == null){
            throw  new GlobalException(CodeMsg.SERVER_ERROR);
        }
        //获取登录信息
        String mobile = loginVO.getMobile();
        String formPass = loginVO.getPassword();
        //通过电话号码获取用户信息
        User user = getByPhoneNum(Long.parseLong(mobile));
        //电话号码不存在则抛出异常信息
        if(user == null){
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        //获取数据库存储的加密后的密码，用于与输入的密码进行比对
        String dbPass = user.getPassword();
        //获取盐值，用于将输入的明文用同样的方式转为密文进行比对
        String salt = user.getSalt();
        String inputPass = MD5Util.encrypt(formPass,salt);
        //进行判断，如果密码不匹配则抛出异常
        if(!MD5Util.equals(inputPass.getBytes(),dbPass.getBytes())){
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        //获取成功，更新cookie和redis中的存储，延长存活时间
        String token = UUIDUtil.uuid();
        updateCache(response,token,user);
        return token;
    }

   public void register(User user){
        String salt = MD5Util.md5(String.valueOf(random.nextInt()));
        String passwordDB = MD5Util.encrypt(user.getPassword(),salt);
        user.setSalt(salt);
        user.setPassword(passwordDB);
        userMapper.add(user);
    }

    /**
     * 通过 id 获取用户信息
     * 先查询缓存，缓存不存在则查询数据库
     * @param phoneNum
     * @return
     */
    public User getByPhoneNum(long phoneNum){
        User user = redisService.get(UserKey.getByPhoneNum,""+phoneNum,User.class);
        if(user != null){
            return user;
        }
        user = userMapper.getByPhoneNum(phoneNum);
        if(user != null){
            redisService.set(UserKey.getByPhoneNum,""+phoneNum,user);
        }
        return user;
    }
    

    /**
     * 更新用户密码
     * @param token k，用于更新缓存中存储的 session
     * @param phoneNum 当前更改密码的用户 phoneNum
     * @param inputPass 新密码
     * @return
     */
    public boolean updatePassword(String token,long phoneNum,String inputPass){
        //先通过用户 phoneNum 获取用户信息，不存在则直接抛出异常
        User user = getByPhoneNum(phoneNum);
        if(user == null){
            throw new GlobalException(CodeMsg.USER_NOT_EXIST);
        }
        User newUser = new User();
        newUser.setId(user.getId());
        newUser.setPassword(MD5Util.encrypt(inputPass,user.getSalt()));
        userMapper.update(newUser);
        //先将缓存中存储的旧 session 删除，再存入信息，以达到更新缓存的目的
        redisService.delete(UserKey.getByPhoneNum,""+phoneNum);
        user.setPassword(newUser.getPassword());
        redisService.set(UserKey.token,token,user);
        return true;
    }

    /**
     * 添加 session 至 缓存中，本地的cookie保存 key
     * @param response
     * @param token key
     * @param user  value
     */
    public void updateCache(HttpServletResponse response,String token,User user){
        //将登陆信息存入缓存，k：token v: user
        redisService.set(UserKey.token,token,user);
        Cookie cookie = CookieUtil.add(CookieUtil.COOKIE_NAME_USER,token);
        response.addCookie(cookie);
    }

    /**
     * 通过 token 获取session
     * @param response
     * @param token k
     * @return
     */
    public User getByToken(HttpServletResponse response, String token){
        if(StringUtils.isEmpty(token)){
            return null;
        }
        User user = redisService.get(UserKey.token,token,User.class);
        //延长有效期
        if(user != null){
            updateCache(response,token,user);
        }
        return user;
    }
}
