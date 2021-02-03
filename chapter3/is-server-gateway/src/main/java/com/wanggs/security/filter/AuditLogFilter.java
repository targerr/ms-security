
package com.wanggs.security.filter;

import com.netflix.zuul.context.RequestContext;
import com.wanggs.security.entity.AuditLog;
import com.wanggs.security.entity.AuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
/**
 * 审计过滤器
 * 1流控--2认证--3审计--4授权
 */

@Slf4j
@Component
public class AuditLogFilter extends ZuulFilter {

    @Autowired(required = false)
    private AuditLogRepository auditLogRepository;
	@Override
	public boolean shouldFilter() {
		return true;
	}


	@Override
	public Object run() throws ZuulException {
		log.info("audit log insert");

        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        AuditLog log = new AuditLog();
        log.setPath(request.getRequestURI());
        log.setMethod(request.getMethod());
        TokenInfo info = (TokenInfo) request.getAttribute("tokenInfo");
        if(info != null){
            log.setUsername(info.getUser_name());
        }
        auditLogRepository.save(log);
        request.setAttribute("auditLogId",log.getId());

		return null;
	}


	@Override
	public String filterType() {
		return "pre";
	}


	@Override
	public int filterOrder() {
        //在OAuthFilter后
		return 2;
	}

}
