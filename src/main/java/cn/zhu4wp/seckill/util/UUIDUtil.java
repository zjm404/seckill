package cn.zhu4wp.seckill.util;

import java.util.UUID;

/**
 * @Author zjm
 * @Date 2020/5/24
 * @Description TODO
 * @Version 1.0
 */
public class UUIDUtil {
    public static String uuid(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
