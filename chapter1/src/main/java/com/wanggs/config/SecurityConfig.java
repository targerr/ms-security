/**
 * 
 */
package com.wanggs.config;

import java.util.Optional;

import com.wanggs.filter.AclInterceptor;
import com.wanggs.filter.AuditLogInterceptor;
import com.wanggs.vo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
//这个是个总开关，把JPA的审计打开。@EnableJpaAuditing
@EnableJpaAuditing
public class SecurityConfig implements WebMvcConfigurer {
	
	@Autowired
	private AuditLogInterceptor auditLogInterceptor;
	@Autowired
	private AclInterceptor aclInterceptor;

	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//.addPathPatterns();//先add的先执行,默认所有请求都拦截
		registry.addInterceptor(auditLogInterceptor);
		registry.addInterceptor(aclInterceptor);
	}
	
//	@Bean
//	public AuditorAware<String> auditorAware() {
//		return new AuditorAware<String>() {
//			@Override
//			public Optional<String> getCurrentAuditor() {
//				ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
//				UserInfo info = (UserInfo)servletRequestAttributes.getRequest().getSession().getAttribute("user");
//				String username = null;
//				if(info != null) {
//					username = info.getUserName();
//				}
//				return Optional.ofNullable(username);
//			}};
//	}
	
}
