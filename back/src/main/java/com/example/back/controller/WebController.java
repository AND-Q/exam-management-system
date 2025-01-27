package com.example.back.controller;

import cn.hutool.core.util.StrUtil;
import com.example.back.common.AuthAccess;
import com.example.back.common.Result;
import com.example.back.entity.User;
import com.example.back.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 功能：提供接口返回数据
 */
// 解决跨域问题的注解    @CrossOrigin        有 CorsConfig.java 后就不需要这个注解了
//@CrossOrigin
@RestController
public class WebController {

    @Resource
    UserService userService;

    @AuthAccess
    @GetMapping("/")
    public Result hello() {
        return Result.success("success");
    }


    @PostMapping("/login")
    public Result login(@RequestBody User user) {
//        常规校验
//        if (user.getUsername() == null || user.getUsername().isEmpty()) {
//            return Result.error("输入不合法");
//        }

//        在pom.xml 导入 hutool-all 用这个校验      国产的工具类依赖
//        isBlank 检测是否为空
        if (StrUtil.isBlank(user.getUsername()) || StrUtil.isBlank(user.getPassword())) {
            return Result.error("输入不合法");
        }
        user = userService.login(user);
        return Result.success(user);
    }

    // 自定义的注解
    @AuthAccess
    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        if (StrUtil.isBlank(user.getUsername()) || StrUtil.isBlank(user.getPassword())) {
            return Result.error("输入不合法");
        }
        // 校验前端传进来的数据 用户名和密码的长度 后端校验安全
        if (user.getUsername().length() > 10 || user.getPassword().length() > 20) {
            return Result.error("输入不合法");
        }
        user = userService.register(user);
        return Result.success(user);
    }
}
