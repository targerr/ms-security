/**
 *
 */
package com.wanggs.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.wanggs.constast.CookieConstant;
import com.wanggs.vo.UserInfo;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


/**
 * 基于ACL访问控制的权限拦截器
 * 在审计之后执行
 */
@Component
public class AclInterceptor extends HandlerInterceptorAdapter {

    private String[] permitUrls = new String[]{"/users/login"};

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        System.out.println(4);

        boolean result = true;

        if (!ArrayUtils.contains(permitUrls, request.getRequestURI())) {
            // 从request拿用户信息
            UserInfo user = (UserInfo) request.getSession().getAttribute(CookieConstant.TOKEN);
            // 没有认证
            if (user == null) {
				//设定类容为json的格式
				response.setContentType("application/json;charset=UTF-8");
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("code",HttpStatus.UNAUTHORIZED.value());
				jsonObject.put("errorMsg","need authentication!");

				response.setStatus(HttpStatus.UNAUTHORIZED.value());
				response.getWriter().write(jsonObject.toJSONString());
				response.getWriter().flush();

				result = false;
            } else {
                //判断用户是否有权限访问
                String method = request.getMethod();
                //没权限
                if (!user.hasPermission(method)) {
					//设定类容为json的格式
					response.setContentType("application/json;charset=UTF-8");
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("code",HttpStatus.FORBIDDEN.value());
					jsonObject.put("errorMsg","Forbidden Request!");

					response.setStatus(HttpStatus.FORBIDDEN.value());
					response.getWriter().write(jsonObject.toJSONString());
					response.getWriter().flush();

                    result = false;
                }
            }
        }
        return result;
    }
}
