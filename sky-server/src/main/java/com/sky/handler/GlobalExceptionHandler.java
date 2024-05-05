package com.sky.handler;

import com.sky.constant.MessageConstant;
import com.sky.exception.BaseException;
import com.sky.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(BaseException ex){
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }

    /**
     * 处理用户名重复异常
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(SQLIntegrityConstraintViolationException ex){
        String result=ex.getMessage();
        if(result.contains("Duplicate entry")) {
            String[] split = result.split(" ");
            String userName = split[2];
            String msg = userName + MessageConstant.ALREADY_EXISTS;
            return Result.error(msg);
        }else return Result.error(MessageConstant.UNKNOWN_ERROR);
    }
    //当指定的异常从 @Controller 中抛出时，就会调用使用了 @ExceptionHandler 注解的方法。这个注解允许你在控制器内部定义异常处理的方法，使得你可以针对不同的异常类型有不同的处理逻辑。
}
