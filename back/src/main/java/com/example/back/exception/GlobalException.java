package com.example.back.exception;

import com.example.back.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.rmi.ServerException;

/**
 * 功能：捕获自定义的异常
 * 作者：Qizhe
 * 日期：2024/12/15  23:06
 */

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(ServiceException.class)
    @ResponseBody       // 把 Result 这个对象转为 JSON
    public Result serviceException(ServiceException e) {
        // 传过来自定义异常的数据  e.getMessage()
        return Result.error(e.getCode(),e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody       // 把 Result 这个对象转为 JSON
    public Result globalException(Exception e) {
        e.printStackTrace();
        // 传过来自定义异常的数据  e.getMessage()
        return Result.error("500","系统错误");
    }
}
