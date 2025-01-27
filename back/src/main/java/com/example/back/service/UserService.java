package com.example.back.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.back.entity.User;
import com.example.back.entity.Uview;

import java.util.List;


/**
 * @author Qizhe
 * @description 针对表【user】的数据库操作Service
 * @createDate 2024-12-08 01:51:21
 */
public interface UserService extends IService<User> {

    boolean save(User entity);

    User selectByUsername(String username);

    User login(User user);

    User register(User user);

    void resetPassword(User user);

    List<User> getAllUsersWithAcademy();
    Page<Uview> getUserPageWithAcademy(int pageNum, int pageSize, String username, String name);
    Integer findAcademyIdByName(String academyName);

}
