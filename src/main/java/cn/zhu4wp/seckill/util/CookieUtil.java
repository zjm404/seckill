package cn.zhu4wp.seckill.util;

import cn.zhu4wp.seckill.redis.UserKey;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Cookie工具类
 * 提供 cookie name
 * 提供 get,set
 * @param <T>
 */
public class CookieUtil<T> {
    public static final String COOKIE_NAME_USER = "SECKILL_USER";

    /**
     * 根据 cookie name 获取 cookie 中存放的信息
     * @param request
     * @param name
     * @param <T>
     * @return
     */
    public static<T> T get(HttpServletRequest request,String name){
        if (request == null){
            throw new NullPointerException("request 为 null");
        }
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return null;
        }
        for (Cookie cookie : cookies) {
            if(name.equals(cookie.getName())){
                return (T) cookie.getValue();
            }
        }
        return null;
    }

    /**
     * 添加 cookie,存活时间与redis中相同数据的存活时间相同
     * @param name
     * @param value
     */
    public static Cookie add(String name,String value){
        //cookie存入token
        Cookie cookie = new Cookie(CookieUtil.COOKIE_NAME_USER,value);
        //cookie存活时间与 redis中的数据存活时间相同
        cookie.setMaxAge(UserKey.token.expireSeconds());
        cookie.setPath("/");
        return cookie;
    }
}
