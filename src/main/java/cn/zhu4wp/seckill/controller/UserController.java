package cn.zhu4wp.seckill.controller;

import cn.zhu4wp.seckill.base.result.Result;
import cn.zhu4wp.seckill.entity.User;
import cn.zhu4wp.seckill.redis.RedisService;
import cn.zhu4wp.seckill.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author zjm
 * @Date 2020/5/25
 * @Description TODO
 * @Version 1.0
 */
@RestController
@RequestMapping("/api")
public class UserController {
    @Resource
    UserService userService;
    @Resource
    RedisService redisService;

    @GetMapping("/info")
    public Result<User> info(User user){
        return Result.success(user);
    }
}
