
package com.wanggs.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class OAuth2AuthServer {


    public static void main(String[] args) {
        SpringApplication.run(OAuth2AuthServer.class, args);
    }

//    @Bean
//	public BCryptPasswordEncoder passwordEncoder(){
//    	return new BCryptPasswordEncoder();
//	}

}
