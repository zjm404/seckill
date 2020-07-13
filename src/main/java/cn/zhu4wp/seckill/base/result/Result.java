package cn.zhu4wp.seckill.base.result;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @Author zjm
 * @Date 2020/5/23
 * @Description TODO
 * @Version 1.0
 */
@Getter
@Setter
public class Result<T> {
    private T data;
    private String msg;
    private int code;

    private Result(CodeMsg codeMsg){
        this.code = codeMsg.getCode();
        this.msg = codeMsg.getMsg();
    }
    private Result(CodeMsg codeMsg,T data){
        this.code = codeMsg.getCode();
        this.msg = codeMsg.getMsg();
        this.data = data;
    }
    private Result(T data){
        this.data = data;
    }


    public static <T>Result<T> error(CodeMsg codeMsg){
        return new Result<T>(codeMsg);
    }
    public static<T> Result<T> success(CodeMsg codeMsg,T data){
        return new Result<T>(codeMsg,data);
    }
    public static<T> Result<T> success(T data){
        return new Result<T>(data);
    }
}
