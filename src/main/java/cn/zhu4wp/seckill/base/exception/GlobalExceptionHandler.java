package cn.zhu4wp.seckill.base.exception;

import cn.zhu4wp.seckill.base.result.CodeMsg;
import cn.zhu4wp.seckill.base.result.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author zjm
 * @Date 2020/5/24
 * @Description TODO
 * @Version 1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHandler(HttpServletRequest request,Exception e){
        if(e instanceof GlobalException){
            GlobalException ex = (GlobalException)e;
            return Result.error(ex.getCodeMsg());
        }else if(e instanceof BindException){
            BindException ex = (BindException) e;
            List<ObjectError> errors = ex.getAllErrors();
            ObjectError error = errors.get(0);
            String msg = error.getDefaultMessage();
            return Result.error(CodeMsg.BIND_ERROR);
        }else{
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }
}
