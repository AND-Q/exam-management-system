package com.example.back.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.back.entity.Examlog;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author Qizhe
* @description 针对表【examlog】的数据库操作Mapper
* @createDate 2024-12-15 23:42:22
* @Entity generator.entity.Examlog
*/
public interface ExamlogMapper extends BaseMapper<Examlog> {
    // 自定义查询方法，带分页和条件查询(舍弃，没有使用)
//    @Select("<script>" +
//            "SELECT * FROM examlog " +
//            "ORDER BY LogID DESC " +
//            "</script>")
//    List<Examlog> getExamLogsPage(Page<Examlog> page);
}




