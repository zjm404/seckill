package cn.zhu4wp.seckill.service.impl;

import cn.zhu4wp.seckill.base.exception.GlobalException;
import cn.zhu4wp.seckill.base.result.CodeMsg;
import cn.zhu4wp.seckill.bean.User;
import cn.zhu4wp.seckill.mapper.UserMapper;
import cn.zhu4wp.seckill.redis.RedisService;
import cn.zhu4wp.seckill.redis.UserKey;
import cn.zhu4wp.seckill.service.UserService;
import cn.zhu4wp.seckill.util.MD5Util;
import cn.zhu4wp.seckill.util.UUIDUtil;
import cn.zhu4wp.seckill.vo.LoginVO;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author zjm
 * @Date 2020/5/24
 * @Description TODO
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;
    @Resource
    RedisService redisService;

    public static final String COOKIE_NAME_TOKEN = "token";

    @Override
    public User getById(long id){
        User user = redisService.get(UserKey.getById,""+id,User.class);
        if(user !=  null){
            return user;
        }
        user = userMapper.getById(id);
        if(user != null){
            redisService.set(UserKey.getById,""+id,user);
        }
        return user;
    }
    @Override
    public boolean updatePassword(String token,long id,String formPass){
        User user = getById(id);
        if(user == null){
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        User newUser = new User();
        newUser.setId(user.getId());
        newUser.setPassword(MD5Util.secondEncrypt(formPass,user.getSalt()));
        userMapper.update(newUser);
        redisService.delete(UserKey.getById,""+id);
        user.setPassword(newUser.getPassword());
        redisService.set(UserKey.token,token,user);
        return true;
    }
    @Override
    public String login(HttpServletResponse response, LoginVO loginVO){
        if(loginVO == null){
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String mobile = loginVO.getMobile();
        String formPass = loginVO.getPassword();
        User user = getById(Long.parseLong(mobile));
        if(user == null){
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        String dbPass = user.getPassword();
        String saltDB = user.getSalt();
        String calcPass = MD5Util.secondEncrypt(formPass,saltDB);
        if(!MD5Util.equals(calcPass.getBytes(), dbPass.getBytes())){
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        String token = UUIDUtil.uuid();
        addCookie(response,token,user);
        return token;
    }


    private void addCookie(HttpServletResponse response, String token, User user) {
        redisService.set(UserKey.token,token,user);
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN,token);
        cookie.setMaxAge(UserKey.token.expireSeconds());
        //设置为网站根目录
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    @Override
    public User getByToken(HttpServletResponse response,String token){
        if(StringUtils.isEmpty(token)){
            return null;
        }
        User user = redisService.get(UserKey.token,token,User.class);
        if(user != null){
            addCookie(response,token,user);
        }
        return user;
    }
}
