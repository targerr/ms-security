/**
 * 
 */
package com.wanggs.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;


//@Configuration
@SpringBootApplication
@EnableResourceServer//作为资源服务器存在
public class OrderApi {

	//声名OAuth2RestTemplate
	//会从请求的上下文里拿到令牌，放到请求头里，发出去。需要两个参数，springboot会自动出入进来
//	@Bean
//	public OAuth2RestTemplate oAuth2RestTemplate(OAuth2ProtectedResourceDetails resource, OAuth2ClientContext context){
//		return new OAuth2RestTemplate(resource,context);
//	}

	public static void main(String[] args) {
		SpringApplication.run(OrderApi.class, args);
	}

}
