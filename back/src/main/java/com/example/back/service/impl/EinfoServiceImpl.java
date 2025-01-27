package com.example.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.back.entity.Einfo;
import com.example.back.mapper.EinfoMapper;
import com.example.back.service.EinfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Qizhe
 * @description 针对表【einfo】的数据库操作Service实现
 * @createDate 2024-12-08 01:51:21
 */
@Service
public class EinfoServiceImpl extends ServiceImpl<EinfoMapper, Einfo> implements EinfoService {
    @Resource
    EinfoMapper einfoMapper;

    @Override
    public Page<Einfo> getUserPageWithTeacher(int pageNum, int pageSize, String username) {
        // 构造分页对象
        Page<Einfo> page = new Page<>(pageNum, pageSize);
        // 打印分页信息，检查 page 是否正常
        System.out.println("Page: " + page);
        System.out.println("Username: " + username);

        // 调用 Mapper 层查询数据
        List<Einfo> userList = einfoMapper.getUserPageWithTeacher(page, username);
        System.out.println("UserList: " + userList);
        // 设置查询结果到分页对象
        page.setRecords(userList);
        return page;
    }
}



