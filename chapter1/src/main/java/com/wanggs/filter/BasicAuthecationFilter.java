package com.wanggs.filter;

import com.wanggs.pojo.User;
import com.wanggs.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: wgs
 * @Date: 2021/1/30
 * <p>
 * 处理httpbasic认证过滤器
 * httpbasic:请求头，即Authorization:Basic 加密字符串
 * 加密字符串为Base64编码的用户名:密码字符串
 * Authorization  Basic bGh5OmxoeWFwcA==
 * 在SpringBoot里，任何实现了Filter接口的类，SpringBoot会自动把它加到web应用的过滤器链里，只要声名为Component就行了
 */
@Order(2)
@Component
@Slf4j
public class BasicAuthecationFilter extends OncePerRequestFilter {
    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println(2);
        String authHeader = request.getHeader("Authorization");
        if (StringUtils.isNotBlank(authHeader)) {

            String token64 = StringUtils.substringAfter(authHeader, "Basic ");
            String token = new String(Base64Utils.decodeFromString(token64));
            String[] items = StringUtils.splitByWholeSeparatorPreserveAllTokens(token, ":");

            String username = items[0];
            String password = items[1];
            logger.info("【请求头Base64 解密】" + username + " " + password);

            User user = userService.findByUsername(username);
            if (user != null && StringUtils.equals(password, user.getPassword())) {
                request.setAttribute("user", user);
            }
        }
        filterChain.doFilter(request, response);
    }
}
