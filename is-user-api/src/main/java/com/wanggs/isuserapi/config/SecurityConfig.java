package com.wanggs.isuserapi.config;

import com.wanggs.isuserapi.filer.AclInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Wgs
 * @version 1.0
 * @create：2020/07/16
 */
//注册拦截器的配置类
@Configuration
public class SecurityConfig implements WebMvcConfigurer {
    @Autowired
    private AclInterceptor aclInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(aclInterceptor);
    }
}
