package com.example.back.exception;

import lombok.Getter;

/**
 * 功能：自定义的异常类
 * 作者：Qizhe
 * 日期：2024/12/15 12:10
 */
@Getter
public class ServiceException extends RuntimeException{

    private final String code;
    public ServiceException(String msg){
        super(msg);
        this.code = "500";
    }

    public ServiceException(String code,String msg){
        super(msg);
        this.code = code;
    }
}
