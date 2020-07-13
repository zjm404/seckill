package cn.zhu4wp.seckill.redis;

/**
 * @Author zjm
 * @Date 2020/5/23
 * @Description TODO
 * @Version 1.0
 */
public interface KeyPrefix {
    /**
     * 有效期
     * @return
     */
    public int expireSeconds();

    /**
     * 前缀
     * @return
     */
    public String getPrefix();

}
