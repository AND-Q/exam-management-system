package com.example.back.common;

import java.lang.annotation.*;

/**
 * 功能：自定义一个注解，在JwtInterceptor.class中写相应的方法，在不需要校验的方法上加上这个注解就可以放权通过
 * 作者：Qizhe
 * 日期：2024/12/15  21:38
 *
 * */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthAccess {
}
