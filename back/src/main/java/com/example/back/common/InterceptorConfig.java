package com.example.back.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 功能：拦截器配置 在这拦截
 * 作者：Qizhe
 * 日期：2024/12/15  17:18
 */

// @Configuration   表示这是一个配置类
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        // 自定义拦截规则和拦截路径
        registry.addInterceptor(jwtInterceptor())   // 配置jwt 的拦截器规则
                .addPathPatterns("/**")   // 拦截所有的请求路径
                .excludePathPatterns("/login");     // 放行登录，放行注册，只有登录完才生成token

        super.addInterceptors(registry);
    }

    @Bean
    public JwtInterceptor jwtInterceptor() {
        return new JwtInterceptor();
    }
}
