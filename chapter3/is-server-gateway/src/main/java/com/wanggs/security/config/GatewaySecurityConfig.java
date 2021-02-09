package com.wanggs.security.config;

import com.wanggs.security.GatewayAccessDeniedHandler;
import com.wanggs.security.GatewayAuthenticationEntryPoint;
import com.wanggs.security.GatewayWebSecurityExpressionHandler;
import com.wanggs.security.entity.AuditLogRepository;
import com.wanggs.security.filter.GatewayAuditLogFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;

/**
 * 作为一个资源服务器存在
 */
@Configuration
@EnableResourceServer
public class GatewaySecurityConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private GatewayWebSecurityExpressionHandler gatewayWebSecurityExpressionHandler;

    @Autowired
    private AuditLogRepository auditLogRepository;

    @Autowired
    private GatewayAccessDeniedHandler accessDeniedHandler;
    @Autowired
    private GatewayAuthenticationEntryPoint authenticationEntryPoint;
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        //资源服务器id
        //resources.resourceId("gateway");
        //注入自己的 表达式处理器
        resources
                .authenticationEntryPoint(authenticationEntryPoint)  //自定义处理401
                .accessDeniedHandler(accessDeniedHandler) //自定义403没有权限的处理逻辑
                //注入自己的 表达式处理器
                .expressionHandler(gatewayWebSecurityExpressionHandler);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                //可以指定过滤器位置，加载授权过滤器前面
                //授权过滤器里，会抛出异常 401或403，这两个异常抛出来后都会由ExceptionTranslationFilter来处理，所以加在这里
//                .addFilterBefore(new GatewayRateLimitFilter(), SecurityContextPersistenceFilter.class)
                .addFilterBefore(new GatewayAuditLogFilter(), ExceptionTranslationFilter.class)
                .authorizeRequests()
                .antMatchers("/token/**").permitAll() //放过/token开头的请求，是在申请令牌
                .anyRequest()
                //指定权限访问规则，permissionService需要自己实现，返回布尔值，true-能访问；false-无权限
                // 传进去2个参数，1-当前请求 ，2-当前用户
                .access("#permissionService.hasPermission(request,authentication)");

    }
}