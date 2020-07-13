package cn.zhu4wp.seckill.util;


import cn.zhu4wp.seckill.base.exception.GlobalException;
import cn.zhu4wp.seckill.base.result.CodeMsg;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * @Author zjm
 * @Date 2020/5/24
 * @Description TODO
 * @Version 1.0
 */
public class MD5Util {
    private static final String salt="&4!a";

    public static String md5(String str){
        return DigestUtils.md5Hex(str);
    }
    /**
     * 网络传输,使用固定的salt
     * @param str
     * @return
     */
    public static String firstEncrypt(String str){
        String msg = "" + salt.charAt(0)+str+salt.charAt(1)+salt.charAt(3)+salt.charAt(2);
        return md5(str);
    }

    /**
     * 传入数据库,使用随机的salt
     * @param str
     * @param dbSalt
     * @return
     */
    public  static String secondEncrypt(String str,String dbSalt){
        if(dbSalt.length() != 4){
            throw new GlobalException(CodeMsg.Encrypt_SALT_ILLEGALITY);
        }
        String msg = ""+dbSalt.charAt(1)+dbSalt.charAt(0)+dbSalt.charAt(2)+str+dbSalt.charAt(3);
        return md5(msg);
    }
    public static String encrypt(String str,String dbSalt){
        String str1 = firstEncrypt(str);
        String str2 = secondEncrypt(str1,dbSalt);
        return str2;
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
