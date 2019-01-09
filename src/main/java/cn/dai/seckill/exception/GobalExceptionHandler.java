package cn.dai.seckill.exception;

import cn.dai.seckill.result.Results;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author adrian
 * @date 2018/10/27 11:11
 **/
@ControllerAdvice
@ResponseBody
public class GobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Results<String> exceptionHandler(HttpServletRequest request, Exception e){
        e.printStackTrace();
        if (e instanceof BindException){
            BindException be = (BindException) e;
            return Results.faild(be.getAllErrors().get(0).getDefaultMessage());
        }else if (e instanceof GobalException){
            GobalException ge = (GobalException) e;
            return Results.faild(ge.getMsg(), ge.getStatus());
        }
        return Results.faild(e.getMessage());
    }

}
