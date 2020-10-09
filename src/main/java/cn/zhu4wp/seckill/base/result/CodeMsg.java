package cn.zhu4wp.seckill.base.result;

import lombok.ToString;

/**
 * @Author zjm
 * @Date 2020/5/23
 * @Description TODO
 * @Version 1.0
 */
@ToString
public enum CodeMsg {
    /**
     * 通用
     */
    SUCCESS("success",0),
    SERVER_ERROR("服务端异常",500100),
    BIND_ERROR("参数校验异常",500101),


    /**
     * 登录错误
     */
    LOGIN_ERROR("登录错误",500200),
    SESSION_ERROR("session不存在或者已失效",500210),
    PASSWORD_EMPTY("登录密码不可以为空",500211),
    MOBILE_EMPTY("手机号不可以为空",500212),
    MOBILE_ERROR("手机号格式错误",500213),
    MOBILE_NOT_EXIST("手机号不存在",500214),
    PASSWORD_ERROR("密码错误",500215),
    PRIMARY_ERROR("主键冲突",500216),
    USER_NOT_EXIST("用户不存在",500217),
    /**
     * 订单错误
     */
    ORDER_NOT_EXIST("订单不存在",500400),
    /**
     * 秒杀错误
     */
    SECKILL_OVER("秒杀结束",500500),
    REPEAT_SECKILL("不可以重复秒杀",500501),
    /**
     * 商品错误
     */
    GOODS_NOT_EXIST("商品不存在",500600),
    /**
     * 加密
     */
    Encrypt_SALT_ILLEGALITY("SALT不合法",500701), ACCESS_LIMIT_REACHED("限制流量",500801 );


    private final String msg;
    private final int code;


    private CodeMsg(String msg,int code){
        this.msg = msg;
        this.code = code;
    }

    public String getMsg(){
        return this.msg;
    }
    public int getCode(){
        return this.code;
    }
}
