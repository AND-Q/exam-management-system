package com.example.back.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

@Data
public class Uview implements Serializable {
    private Integer id;            // 用户ID
    private String username;    // 用户名
    private String password;    // 密码
    private String phone;       // 电话
    private String email;       // 邮箱
    private String avatar;      // 头像
    private String academy;     // 学院名称 (来自 academy.name)
    private String name;        // 用户姓名
}