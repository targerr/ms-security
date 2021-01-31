package com.wanggs.security.server;

/**
 * @Author: wgs
 * @Date: 2021/1/31
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * 资源服务器
 * 配置了@EnableResourceServer ，所有发往nb-order-api的请求，都会去请求头里找token，找不到不让你过
 */
@Configuration
@EnableResourceServer//告诉nb-order-api,你就是资源服务器
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        //配置资源服务器的id，要和server_auth .resourceIds("order-server")一样
        // “现在我就是资源服务器order-server！！！”
        // 图示 https://upload-images.jianshu.io/upload_images/4994935-3a141d90ea35ba8d.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240
        resources.resourceId("order-server");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        /**
         * 进入order-api的所有请求，哪些要拦截，哪些要放过，在这里配置
         */
//        http.authorizeRequests()
//                .antMatchers("/hello")
//                //放过/haha不拦截
//                .permitAll()
//                //其余所有请求都拦截
//                .anyRequest().authenticated();


        http.authorizeRequests()
                // 定义post只有写权限
                .antMatchers(HttpMethod.POST).access("#oauth2.hasScope('write')")
                // 定义get请求只有读权限
                .antMatchers(HttpMethod.GET).access("#oauth2.hasScope('read')");
    }
}
