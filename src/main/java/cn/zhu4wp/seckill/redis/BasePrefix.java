package cn.zhu4wp.seckill.redis;

/**
 * @Author zjm
 * @Date 2020/5/23
 * @Description TODO
 * @Version 1.0
 */
public class BasePrefix implements KeyPrefix{

    private int expireSeconds;

    private String prefix;

    public BasePrefix(String prefix){
        this(0,prefix);
    }
    public BasePrefix(int expireSeconds,String prefix){
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }
    @Override
    public int expireSeconds() {
        return this.expireSeconds;
    }

    @Override
    public String getPrefix() {
        String className = getClass().getSimpleName();
        return className+":"+this.prefix;
    }
}
