package com.example.back.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.back.common.Result;
import com.example.back.entity.Einfo;
import com.example.back.service.EinfoService;
import com.example.back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;



/**
 * 功能：接收前端发送的关于监考信息CRUD请求
 * 作者：qizhe
 * 日期：2024/12/15 19:55
 */

@RestController
@RequestMapping("/einfo")
public class EinfoController {

    @Autowired
    EinfoService einfoService;

    /**
     * 新建用户信息
     */
    //    @RequestBody 将对象数据转为JSON数据
    //    从前端接收user，并传到UserService
    @Autowired
    private UserService userService;  // 确保通过 Spring 注入了 userService

    @PostMapping("/add")
    public Result add(@RequestBody Einfo einfo) {
        try {
            // 校验teacher字段是否为空
//            if (einfo.getTeacher() == null || einfo.getTeacher().isEmpty()) {
//                return Result.error("教师字段不能为空");
//            }
//            // 校验teacher是否在user表中存在
//            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//            queryWrapper.eq("username", einfo.getTeacher());  // 根据教师的username查找
//            User teacher = userService.getOne(queryWrapper);
//
//            if (teacher == null) {
//                return Result.error("教师不存在，请检查教师名称！");
//            }
            // 打印einfo，调试用
//            System.out.println("einfo: " + einfo);
            // 保存einfo

//            if (einfo.getTeacher() != null && einfo.getTeacher().contains(","))//有两个老师
//            {
//                String[] teachers = einfo.getTeacher().split(",");
//                Einfo info1 = new Einfo();
//                Einfo info2 = new Einfo();
//                // 设置第一个教师
//                info1.setTeacher(teachers[0].trim());
//                info1.setId(einfo.getId());
//                info1.setUsername(einfo.getUsername());
//                info1.setPosition(einfo.getPosition());
//                info1.setTime(einfo.getTime());
//                einfoService.save(info1);
//                // 设置第二个教师
//                info2.setTeacher(teachers[1].trim());
//                info2.setId(einfo.getId());
//                info2.setUsername(einfo.getUsername());
//                info2.setPosition(einfo.getPosition());
//                info2.setTime(einfo.getTime());
//                einfoService.save(info2);
//            }
//            else
//            {
//                einfoService.save(einfo);
//            }


            einfoService.save(einfo);
//            System.out.println("success");
        } catch (Exception e) {
            e.printStackTrace();  // 打印异常堆栈信息
//            System.out.println("Error Message: " + e.getMessage());  // 打印具体的错误信息

            if (e instanceof DuplicateKeyException) {
                return Result.error("插入数据错误");
            } else {
                return Result.error("系统错误");
            }
        }
        return Result.success();
    }

    /**
     * 修改用户信息
     */
    //    @RequestBody 将对象数据转为JSON数据
    //    从前端接收user，并传到UserService
    @PutMapping("/update")
    public Result update(@RequestBody Einfo einfo) {
        einfoService.updateById(einfo);
        return Result.success();
    }

    /**
     * 删除用户信息
     * 单个删除
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        einfoService.removeById(id);
        return Result.success();
    }

    /**
     * 查询用户信息
     * 分页多条件的模糊查询
     * pageNum 当前页码     pageSize 当前页码的个数
     */
    @GetMapping("/selectByPage")
    public Result selectByPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam String username) {

        System.out.println("pageNum:" + pageNum);
        System.out.println("pageSize:" + pageSize);
        System.out.println("username:" + username);
        // 调用 Service 层获取分页结果
        Page<Einfo> page = einfoService.getUserPageWithTeacher(pageNum, pageSize, username);
        return Result.success(page);
    }
}
