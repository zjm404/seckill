package cn.zhu4wp.seckill.util;


import org.apache.commons.codec.digest.DigestUtils;

/**
 * @Author zjm
 * @Date 2020/5/24
 * @Description TODO
 * @Version 1.0
 */
public class MD5Util {
    public static String md5(String str){
        return DigestUtils.md5Hex(str);
    }

    public static String encrypt(String str,String salt){
        return md5(str+salt);
    }

    /**
     * 时间恒定比较，避免攻击者根据验证的时间长短来辅助判断
     * 参考自
     * https://blog.coderzh.com/2016/01/03/security-design/
     * @param a
     * @param b
     * @return
     */
    public static boolean equals(byte[] a,byte[] b){
        int diff = a.length ^ b.length;
        for (int i = 0; i < a.length && i < b.length; i++){
            diff |= a[i] ^ b[i];
        }
        return diff == 0;
    }
}
