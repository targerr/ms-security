package com.wanggs.filter;

import com.alibaba.fastjson.JSONObject;
import com.google.common.util.concurrent.RateLimiter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 继承 OncePerRequestFilter 保证过滤器里的逻辑在一个请求里只会被过滤一次
 * 在SpringBoot里，任何实现了Filter接口的类，SpringBoot会自动把它加到web应用的过滤器链里，只要声名为Component就行了
 *
 * @author wgshu
 */
@Order(1)//执行顺序
@Component
public class RateLimiterFilter extends OncePerRequestFilter {
    /**
     * 每秒1个请求的限流器
     */
    private RateLimiter rateLimiter = RateLimiter.create(1);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.err.println(">>>进入流控<<<");

        if (rateLimiter.tryAcquire()) {
            //如果没达到限流阈值，放行
            filterChain.doFilter(request, response);
        } else {
            //设定类容为json的格式
            response.setContentType("application/json;charset=UTF-8");

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code",HttpStatus.TOO_MANY_REQUESTS.value());
            jsonObject.put("errorMsg","too many request!");

            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.getWriter().write(jsonObject.toJSONString());
            response.getWriter().flush();
        }
    }
}
