package cn.zhu4wp.seckill.service;

import cn.zhu4wp.seckill.bean.User;
import cn.zhu4wp.seckill.vo.LoginVO;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author zjm
 * @Date 2020/5/23
 * @Description TODO
 * @Version 1.0
 */

public interface UserService {
    public User getById(long id);

    public boolean updatePassword(String token,long id,String formPass);

    public String login(HttpServletResponse response, LoginVO loginVO) ;

    public User getByToken(HttpServletResponse response,String token);
}
