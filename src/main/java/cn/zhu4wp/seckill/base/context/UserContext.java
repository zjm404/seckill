package cn.zhu4wp.seckill.base.context;

import cn.zhu4wp.seckill.entity.User;

/**
 * 将通过 Session 获取到的 User 保存到 ThreadLocal
 */
public class UserContext {
    private static ThreadLocal<User> userHolder = new ThreadLocal<>();

    public static void setUser(User user){
        userHolder.set(user);
    }

    public static User getUser(){
        return userHolder.get();
    }

    public static void deleteUser(){
        userHolder.remove();
    }
}
