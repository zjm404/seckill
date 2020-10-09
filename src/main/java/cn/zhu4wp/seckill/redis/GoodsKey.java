package cn.zhu4wp.seckill.redis;

public class GoodsKey extends BasePrefix{
    public GoodsKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static GoodsKey getGoodsList(){
        return new GoodsKey(60,"g1");
    }
    public static GoodsKey getGoodsDetail(){
        return new GoodsKey(60,"gd");
    }
    public static GoodsKey getGoodsStock(){
        return new GoodsKey(0,"gs");
    }
}
