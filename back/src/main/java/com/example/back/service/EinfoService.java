package com.example.back.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.back.entity.Einfo;

/**
 * @author Qizhe
 * @description 针对表【einfo】的数据库操作Service
 * @createDate 2024-12-08 01:51:21
 */

public interface EinfoService extends IService<Einfo> {
    //    Einfo selectEinfoWithTeachers(String username);
    Page<Einfo> getUserPageWithTeacher(int pageNum, int pageSize, String username);
}

