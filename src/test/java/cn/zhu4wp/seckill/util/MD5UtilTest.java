package cn.zhu4wp.seckill.util;

import org.junit.jupiter.api.Test;

class MD5UtilTest {

    @Test
    void md5() {
    }

    @Test
    void encrypt() {
        String msg = MD5Util.encrypt("admin","demo");
        System.out.println(msg);
    }

    @Test
    void testEquals() {
        String msg1 = "hello";
        String msg2 = "hello";
        System.out.println(MD5Util.equals(msg1.getBytes(),msg2.getBytes()));

    }
}