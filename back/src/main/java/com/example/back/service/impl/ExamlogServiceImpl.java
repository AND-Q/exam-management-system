package com.example.back.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.back.entity.Examlog;
import com.example.back.mapper.ExamlogMapper;
import com.example.back.service.ExamlogService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author Qizhe
 * @description 针对表【examlog】的数据库操作Service实现
 * @createDate 2024-12-15 23:42:22
 */
@Service
public class ExamlogServiceImpl extends ServiceImpl<ExamlogMapper, Examlog>
        implements ExamlogService {

    @Resource
    private ExamlogMapper examlogMapper; // 正确注入ExamlogMapper

//    /**
//     * 获取分页的考试日志信息，支持按日志内容进行查询
//     *
//     * @param pageNum 当前页码
//     * @param pageSize 每页显示条数
//     * @return 分页后的日志列表
//     */
//    public Page<Examlog> getExamLogsPage(int pageNum, int pageSize) {
//        // 构造分页对象
//        Page<Examlog> page = new Page<>(pageNum, pageSize);
//
//        // 调用 Mapper 层查询数据
//        List<Examlog> examlogList = examlogMapper.getExamLogsPage(page);
//
//        // 设置查询结果到分页对象
//        page.setRecords(examlogList);
//        return page;
//    }
}
