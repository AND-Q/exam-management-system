package com.example.back.utils;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.back.entity.User;
import com.example.back.mapper.UserMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 功能：后端生成token
 * 作者：hwr
 * 日期：2024/12/12 12:15
 */

@Component
public class TokenUtils {

    // 写一个静态的UserMapper,可以被类去访问
    private static UserMapper staticUserMapper;

    @Resource
    UserMapper userMapper;

    @PostConstruct
    public void setUserService() {
        staticUserMapper = userMapper;
    }

    /**
     * 生成token
     * @return
     */
    public static String createToken(String userId, String sign) {
        return JWT.create().withAudience(userId)    // 将 usre id 保存到token里面，作为载荷
                .withExpiresAt(DateUtil.offsetHour(new Date(), 2))  // 两小时后token过期
                .sign(Algorithm.HMAC256(sign)); // 以password 作为 token 的秘钥 这里传的是加密的，所以校验那里也要是加密的验证器
    }

    /**
     * 获取当前登录的用户信息
     * @return user对象
     * */
    public static User getCurrentUser() {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String token = request.getHeader("token");
            if (StrUtil.isBlank(token)) {
                String userId = JWT.decode(token).getAudience().get(0);
                return staticUserMapper.selectById(Integer.valueOf(userId));
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }
}
