package com.example.back.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.back.entity.Einfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.back.entity.Uview;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author Qizhe
* @description 针对表【einfo】的数据库操作Mapper
* @createDate 2024-12-08 11:48:05
* @Entity com.example.back.entity.Einfo
*/
public interface EinfoMapper extends BaseMapper<Einfo> {

    @Select("<script>" +
            "SELECT " +
            "  e.id, e.username, e.position, e.time, " +
            "  GROUP_CONCAT(u.username ORDER BY u.username ASC SEPARATOR ', ') AS teacher " +
            "FROM " +
            "  exam ex " +
            "LEFT JOIN " +
            "  einfo e ON ex.infoid = e.id " +
            "LEFT JOIN " +
            "  user u ON ex.uid = u.id " +
            "WHERE 1=1 " +
            "<if test='username != null and username.trim() != \"\"'>" +
            "  AND e.username LIKE CONCAT('%', #{username}, '%') " +
            "</if>" +
            "GROUP BY e.id " +
            "ORDER BY e.id DESC" +
            "</script>")
    List<Einfo> getUserPageWithTeacher(@Param("page") Page<Einfo> page,
                                       @Param("username") String username);
}




