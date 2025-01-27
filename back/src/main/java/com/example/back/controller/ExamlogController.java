package com.example.back.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.back.common.Result;
import com.example.back.entity.Examlog;
import com.example.back.service.ExamlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 功能：接收前端发送的关于考试日志CRUD请求
 * 作者：qizhe
 * 日期：2024/12/15 19:55
 */
@CrossOrigin(origins = "*")  // 允许所有来源的请求
@RestController
@RequestMapping("/examlog")
public class ExamlogController {

    @Autowired
    private ExamlogService examlogService;

    /**
     * 查询考试日志
     * 分页多条件的模糊查询
     * pageNum 当前页码     pageSize 当前页码的个数
     */
//    @GetMapping("/selectByPage")
//    public Result selectByPage(@RequestParam Integer pageNum,
//                               @RequestParam Integer pageSize) {
//        System.out.println("pageNum:" + pageNum);
//        System.out.println("pageSize:" + pageSize);
//        System.out.println("dashfkashfkl");
//        // 调用 Service 层获取分页结果
//        Page<Examlog> page = examlogService.getExamLogsPage(pageNum, pageSize);
//        return Result.success(page);
//    }
        @GetMapping("/selectByPage")
    public Result selectByPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize){
        // 条件查询器
        QueryWrapper<Examlog> queryWrapper = new QueryWrapper<Examlog>().orderByDesc("LogID");
        Page<Examlog> page = examlogService.page(new Page<>(pageNum, pageSize), queryWrapper);
        return Result.success(page);
    }
}
