
package com.wanggs.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wanggs.pojo.AuditLog;
import com.wanggs.repository.AuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 审计日志拦截器
 * 拦截流程
 * 流控 -- 认证 --审计 -- 授权 -- 业务
 * 审计要在进入接口之前，insert 数据库（实际可能发送到专门的日志服务器），执行完后 update，过滤器不便于判断拦截之前、之后，故用拦截器
 */
@Component
public class AuditLogInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private AuditLogRepository auditLogRepository;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println(3);

		AuditLog log = new AuditLog();
		log.setMethod(request.getMethod());
		log.setPath(request.getRequestURI());
		
		auditLogRepository.save(log);
		
		request.setAttribute("auditLogId", log.getId());
		
		return true;
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		Long auditLogId = (Long) request.getAttribute("auditLogId");
		
		AuditLog log = auditLogRepository.findById(auditLogId).get();
		
		log.setStatus(response.getStatus());
		
		auditLogRepository.save(log);
		
	}

}
