package com.wanggs.security.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

/**
 * 怎么验发往本服务的请求头的令牌
 * 1，自定义tokenServices ,说明去哪里去验token
 * 2，重写authenticationManagerBean()方法，将AuthenticationManager暴露为一个Bean
 *    要认证跟用户相关的信息，一般用 AuthenticationManager
 *
 * 这样配置了后,所有发往nb-order-api的请求，
 * 需要验token的时候就会发请求去http://localhost:9090/oauth/check_token验token，获取到token对应的用户信息
 */
@Configuration
@EnableWebSecurity
public class OAuth2WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 通过这个Bean，去远程调用认证服务器，验token
     * @return
     */
    @Bean
    public ResourceServerTokenServices tokenServices(){
        // 图示 https://upload-images.jianshu.io/upload_images/4994935-12a637f19027d44f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240

        RemoteTokenServices tokenServices = new RemoteTokenServices();
        //在认证服务器配置的，订单服务的clientId
        tokenServices.setClientId("orderServer");
        //在认证服务器配置的，订单服务的ClientSecret
        tokenServices.setClientSecret("123456");
        // 必须要和认证服务器地址一样
        tokenServices.setCheckTokenEndpointUrl("http://localhost:9090/oauth/check_token");
        return tokenServices;
    }


    /**
     * 要认证跟用户相关的信息，一般用 AuthenticationManager
     * 覆盖这个方法，可以将AuthenticationManager暴露为一个Bean
     *
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        OAuth2AuthenticationManager authenticationManager = new OAuth2AuthenticationManager();
        //设置为自定义的TokenServices，去校验令牌
        authenticationManager.setTokenServices(tokenServices());
        return authenticationManager;
    }
}
