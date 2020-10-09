package cn.zhu4wp.seckill.redis;

/**
 * @Author zjm
 * @Date 2020/5/24
 * @Description TODO
 * @Version 1.0
 */
public class UserKey extends BasePrefix{
    public static final int TOKEN_EXPIRE = 3600*24*2;
    public static UserKey token = new UserKey(TOKEN_EXPIRE,"token");
    public static UserKey getByPhoneNum = new UserKey(0,"phoneNum");

    public UserKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }


}
