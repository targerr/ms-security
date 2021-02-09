package com.wanggs.security;

import com.alibaba.fastjson.JSON;
import com.wanggs.security.entity.AuditLog;
import com.wanggs.security.entity.AuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义403异常处理器，可以自定义响应信息
 */
@Component
public class GatewayAccessDeniedHandler extends OAuth2AccessDeniedHandler {

    @Autowired
    private AuditLogRepository auditLogRepository;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException authException) throws  ServletException, IOException {

        //更新日志信息
        Long logId = (Long)request.getAttribute("logId");
        if(logId != null){
            AuditLog log = new AuditLog();
            log.setId(logId);
            log.setModifyTime(new Date());
            log.setStatus(response.getStatus());
            auditLogRepository.save(log);
        }
        //super.handle(request, response, authException); //默认处理
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("status",403);
        resultMap.put("msg","sorry! 403");
        response.getWriter().write(JSON.toJSONString(request));

        //通知审计日志过滤器，403已经被处理过的标识，那里加个判断，否则就会更新两次
        request.setAttribute("logUpdated","yes");
    }
}
