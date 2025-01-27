package com.example.back.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.back.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.back.entity.Uview;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author Qizhe
* @description 针对表【user】的数据库操作Mapper
* @createDate 2024-12-08 11:48:05
* @Entity com.example.back.entity.User
*/
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT u.id, u.username, u.name, u.phone, u.email, u.avatar, u.role, a.academy AS academy " +
            "FROM user u " +
            "LEFT JOIN academy a ON u.academy = a.id " +
            "ORDER BY u.id DESC")
    List<User> selectUserWithAcademy();

    @Select("<script>" +
            "SELECT u.id, u.username, u.name, u.phone, u.email, u.avatar, a.academy AS academy, u.password " +
            "FROM user u " +
            "LEFT JOIN academy a ON u.academy = a.id " +
            "WHERE 1=1 " +
            "<if test='username != null and username.trim() != \"\"'> " +
            "  AND u.username LIKE CONCAT('%', #{username}, '%') " +
            "</if> " +
            "<if test='name != null and name.trim() != \"\"'> " +
            "  AND u.name LIKE CONCAT('%', #{name}, '%') " +
            "</if> " +
            "ORDER BY u.id DESC " +
            "</script>")
    List<Uview> selectUserWithAcademyByPage(@Param("page") Page<Uview> page,
                                            @Param("username") String username,
                                            @Param("name") String name);
    @Select("SELECT id FROM academy WHERE academy = #{academy}")
    Integer findAcademyIdByName(String academy);

}




