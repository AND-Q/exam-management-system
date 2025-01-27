package com.example.back;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackApplication {
//    @MapperScan("com.example.back.mapper")

    public static void main(String[] args) {
        SpringApplication.run(BackApplication.class, args);
    }

}
