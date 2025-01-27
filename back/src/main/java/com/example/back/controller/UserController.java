package com.example.back.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.back.common.Result;
import com.example.back.entity.User;
import com.example.back.entity.Uview;
import com.example.back.exception.ServiceException;
import com.example.back.service.UserService;
import com.example.back.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 功能：实现user的增删改查
 * 作者：Qizhe
 * 日期：2024/12/15 11:48
 */

// 解决跨域问题的注解    @CrossOrigin        有 CorsConfig.java 后就不需要这个注解了
//@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 新建教师用户信息
     * */
    //    @RequestBody 将对象数据转为JSON数据
    //    从前端接收user，并传到UserService
    @PostMapping("/add")
    public Result add(@RequestBody Uview user){
        try {
            User user_true = new User(); // 确保初始化为非 null 对象

            Integer item = userService.findAcademyIdByName(user.getAcademy());

            if (item == null) {
                throw new ServiceException("Academy ID not found");
            }

            //这里从Uview中获取数据，并赋值给User
            user_true.setId(user.getId());
            user_true.setUsername(user.getUsername());
            user_true.setAvatar(user.getAvatar());
            user_true.setName(user.getName());
            user_true.setEmail(user.getEmail());
            user_true.setPhone(user.getPhone());
            user_true.setAcademy(item);

            userService.save(user_true);

        } catch (Exception e) {
            if(e instanceof DuplicateKeyException) {
                return Result.error("插入数据错误");
            } else {
                return Result.error("系统错误");
            }
        }
        return Result.success();
    }

    /**
     * 修改教师信息
     * */
    //    @RequestBody 将对象数据转为JSON数据
    //    从前端接收user，并传到UserService
    @PutMapping ("/update")
    public Result update(@RequestBody Uview user){
//        User currentUser = TokenUtils.getCurrentUser();
//        if (user.getId().equals(currentUser.getId())) {
//            throw new ServiceException("请前往个人信息修改");
//        }
//        userService.updateById(user); // 执行更新操作
        User user_true = new User(); // 确保初始化为非 null 对象

        Integer item = userService.findAcademyIdByName(user.getAcademy());

        if (item == null) {
            throw new ServiceException("Academy ID not found");
        }

        //这里从Uview中获取数据，并赋值给User
        user_true.setId(user.getId());
        user_true.setUsername(user.getUsername());
        user_true.setName(user.getName());
        user_true.setAvatar(user.getAvatar());
        user_true.setEmail(user.getEmail());
        user_true.setPhone(user.getPhone());
        user_true.setAcademy(item);

//        System.out.println("处理前： "+ user);
//        System.out.println("处理后： "+ user_true);

        userService.updateById(user_true);
        return Result.success();
    }

    /**
     * 删除教师用户信息
     * 单个删除
     * */

    @DeleteMapping ("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        User currentUser = TokenUtils.getCurrentUser();
        if (currentUser != null && currentUser.getId() != null && id != null && id.equals(currentUser.getId())) {
            throw new ServiceException("不能删除当前登录的用户");
        }
        userService.removeById(id);
        return Result.success();
    }

    /**
     * 删除教师用户信息
     * 批量删除
     * */
    //    @RequestBody 将对象数据转为JSON数据
    //    从前端接收user，并传到UserService
    @DeleteMapping ("/delete/batch")
    public Result batchdelete(@RequestBody List<Integer> ids){
//        User currentUser = TokenUtils.getCurrentUser();
//        if(currentUser != null && currentUser.getId() != null && ids.contains(currentUser.getId())) {
//            throw new ServiceException("不能删除当前登录的用户");
//        }
        userService.removeBatchByIds(ids);
        return Result.success();
    }

    /**
     * 查询用户信息
     * 全部查询
     * */

    @GetMapping("/selectUserAll")
    public Result selectUserAll() {
        List<User> userList = userService.getAllUsersWithAcademy();
        System.out.println(userList);
        return Result.success(userList);
    }

    /**
     * 查询用户信息
     * 条件查询,根据id
     * */
//    /{id} 路径参数
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id){
        User user = userService.getById(id);
        return Result.success(user);
    }

    /**
     * 查询用户信息
     * 分页多条件的模糊查询
     * pageNum 当前页码     pageSize 当前页码的个数
     * */
//    @RequestParam Url参数
//    @GetMapping("/selectByPage")
//    public Result selectByPage(@RequestParam Integer pageNum,
//                               @RequestParam Integer pageSize,
//                               @RequestParam String username,
//                               @RequestParam String name){
////        System.out.println(username);
//        // 条件查询器
//        QueryWrapper<User> queryWrapper = new QueryWrapper<User>().orderByDesc("id");
//        // eq => ==  where username = #{username}
//        queryWrapper.like(StrUtil.isNotBlank(username),"username",username);
//        queryWrapper.like(StrUtil.isNotBlank(name),"name",name);
//        // select * from user where username like '%#{username}%' and name like '%{name}%'
//        Page<User> page = userService.page(new Page<>(pageNum, pageSize), queryWrapper);
//        return Result.success(page);
//    }

    @GetMapping("/selectByPage")
    public Result selectByPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam(required = false) String username,
                               @RequestParam(required = false) String name) {

        System.out.println("pageNum:"+pageNum);
        System.out.println("pageSize:"+pageSize);
        System.out.println("username:"+username);
        System.out.println("name:"+name);
        // 调用 Service 层获取分页结果
        Page<Uview> page = userService.getUserPageWithAcademy(pageNum, pageSize, username, name);
        System.out.println("page:"+page);
        return Result.success(page);
    }
}
