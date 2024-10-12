package com.cq.kt.vod.exception;

import com.cq.kt.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Tool:IntelliJ IDEA
 * Date:2024-06-22-19:48
 * Description:
 *
 * @ Author:两袖青蛇
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        System.out.println("#################################"+"执行了全局异常处理");
        return Result.fail().message("执行了全局异常处理");
    }
}
