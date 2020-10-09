package cn.zhu4wp.seckill.redis;

public class OrderKey extends BasePrefix{
    public OrderKey(String prefix) {
        super(prefix);
    }
    public static OrderKey getSeckillOrderByUidAndGid(){
        return new OrderKey("seckill");
    }
}
