package cn.zhu4wp.seckill.util.validator;

import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author zjm
 * @Date 2020/5/23
 * @Description TODO
 * @Version 1.0
 */
public class ValidatorUtil {
    /**
     * 验证是否是手机号
     */
    private static final Pattern mobilePattern = Pattern.compile("1\\d{10}");

    public static boolean isMobile(String str){
        if(StringUtils.isEmpty(str)){
            return false;
        }
        Matcher m = mobilePattern.matcher(str);
        return m.matches();
    }
}
