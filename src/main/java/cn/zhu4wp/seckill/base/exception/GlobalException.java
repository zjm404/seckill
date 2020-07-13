package cn.zhu4wp.seckill.base.exception;

import cn.zhu4wp.seckill.base.result.CodeMsg;

/**
 * @Author zjm
 * @Date 2020/5/24
 * @Description TODO
 * @Version 1.0
 */
public class GlobalException extends RuntimeException{
    private CodeMsg codeMsg;
    public GlobalException(CodeMsg codeMsg){
        super(codeMsg.toString());
        this.codeMsg = codeMsg;
    }
    public CodeMsg getCodeMsg(){
        return codeMsg;
    }
}
