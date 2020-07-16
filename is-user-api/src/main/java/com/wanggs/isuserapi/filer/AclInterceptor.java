package com.wanggs.isuserapi.filer;

import com.alibaba.fastjson.JSONObject;
import com.wanggs.isuserapi.pojo.User;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Wgs
 * @version 1.0
 * @create：2020/07/16
 */
@Component
public class AclInterceptor extends HandlerInterceptorAdapter {
    private String[] permitUrls = new String[]{"/login"};

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //true表示请求放行，false表示拦截请求，不再向下寻找处理器
        System.out.println(3);
        boolean result = true;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        JSONObject res = new JSONObject();
        if (!ArrayUtils.contains(permitUrls, request.getRequestURI())) {
            User user = (User) request.getSession().getAttribute("user");
            if (user != null) {
                String method = request.getMethod();
                if (!doGetUserPermiss(method)) {
                    res.put("status", HttpStatus.FORBIDDEN.value());
                    res.put("msg", "forbidden");
                    response.getWriter().write(res.toJSONString());
                    result = false;
                }
            } else {
                res.put("status", HttpStatus.UNAUTHORIZED.value());
                res.put("msg", "need authentication");
                response.getWriter().write(res.toJSONString());
                result = false;
            }
        }
        return result;
    }

    private boolean doGetUserPermiss(String method) {
        boolean result = false;
        // 用户数据库存入的权限 r:只读; w: 写
        String permissions = "r";
        if (StringUtils.equalsIgnoreCase("get", method)) {
            result = StringUtils.contains(permissions, "r");
        } else {
            result = StringUtils.contains(permissions, "w");
        }
        return result;
    }
}
