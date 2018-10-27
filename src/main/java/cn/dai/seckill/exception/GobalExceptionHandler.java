package cn.dai.seckill.exception;

import cn.dai.seckill.result.Results;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @author adrian
 * @date 2018/10/27 11:11
 **/
@ControllerAdvice
public class GobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Results<String> exceptionHandler(HttpServletRequest request, Exception e){
        e.printStackTrace();
        if (e instanceof BindException){
            BindException be = (BindException) e;
            return Results.faild(be.getAllErrors().get(0).getDefaultMessage());
        }
        return Results.faild(e.getMessage());
    }

}