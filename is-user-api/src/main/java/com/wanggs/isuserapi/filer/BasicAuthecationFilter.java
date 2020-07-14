package com.wanggs.isuserapi.filer;

import com.lambdaworks.crypto.SCryptUtil;
import com.wanggs.isuserapi.UserService;
import com.wanggs.isuserapi.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Wgs
 * @version 1.0
 * @create：2020/07/13
 */
@Slf4j
@Component
public class BasicAuthecationFilter extends OncePerRequestFilter {
    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
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
