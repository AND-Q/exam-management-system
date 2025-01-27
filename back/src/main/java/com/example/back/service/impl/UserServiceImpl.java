package com.example.back.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.back.entity.User;
import com.example.back.entity.Uview;
import com.example.back.exception.ServiceException;
import com.example.back.mapper.UserMapper;
import com.example.back.service.UserService;
import com.example.back.utils.TokenUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Qizhe
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2024-12-08 01:51:21
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public boolean save(User entity) {
        if (StrUtil.isBlank(entity.getName())) {
            entity.setName(entity.getUsername());
        }
        if (StrUtil.isBlank(entity.getPassword())) {
            entity.setPassword("123");
        }
        if (StrUtil.isBlank(entity.getAvatar())) {
            String defaultAvatarPath = "http://localhost:8081/file/download/moren.jpg";
            entity.setAvatar(defaultAvatarPath);
        }
        return super.save(entity);
    }

    @Override
    public User selectByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return this.getOne(queryWrapper);
    }

    @Override
    public User login(User user) {
        User dbUser = selectByUsername(user.getUsername());
        if (dbUser == null) {
            throw new ServiceException("用户名或密码错误");
        }
        if (!user.getPassword().equals(dbUser.getPassword())) {
            throw new ServiceException("用户名或密码错误");
        }
        String token = TokenUtils.createToken(dbUser.getId().toString(), dbUser.getPassword());
        dbUser.setToken(token);
        return dbUser;
    }

    @Override
    public User register(User user) {
        User dbUser = selectByUsername(user.getUsername());
        if (dbUser != null) {
            throw new ServiceException("用户名已存在");
        }
        user.setName(user.getUsername());
        String defaultAvatarPath = "http://localhost:8081/file/download/moren.jpg";
        user.setAvatar(defaultAvatarPath);
        userMapper.insert(user);
        return user;
    }

    @Override
    public void resetPassword(User user) {
        User dbUser = selectByUsername(user.getUsername());
        if (dbUser == null) {
            throw new ServiceException("用户不存在");
        }
        if (!user.getPhone().equals(dbUser.getPhone())) {
            throw new ServiceException("验证错误");
        }
        dbUser.setPassword("123");
        updateById(dbUser);
    }
    @Override
    public List<User> getAllUsersWithAcademy() {
        return userMapper.selectUserWithAcademy();
    }
    @Override
    public Page<Uview> getUserPageWithAcademy(int pageNum, int pageSize, String username, String name) {
        // 构造分页对象
        Page<Uview> page = new Page<>(pageNum, pageSize);
        // 打印分页信息，检查 page 是否正常
        System.out.println("Page: " + page);
        System.out.println("Username: " + username);
        System.out.println("Name: " + name);

        // 调用 Mapper 层查询数据
        List<Uview> userList = userMapper.selectUserWithAcademyByPage(page, username, name);
        System.out.println();
        System.out.println("UserList: " + userList);
        // 设置查询结果到分页对象
        page.setRecords(userList);
        return page;
    }
    //根据academy查找academy表格中的id
    @Override
    public Integer findAcademyIdByName(String academyName) {
        return userMapper.findAcademyIdByName(academyName);
    }

}




