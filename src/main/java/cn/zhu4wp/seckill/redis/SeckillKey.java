package cn.zhu4wp.seckill.redis;

public class SeckillKey extends BasePrefix{
    public SeckillKey(String prefix) {
        super(prefix);
    }
    public static SeckillKey isGoodsOver(){
        return new SeckillKey("go");
    }
}
