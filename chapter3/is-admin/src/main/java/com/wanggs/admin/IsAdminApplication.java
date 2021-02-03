package com.wanggs.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class IsAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(IsAdminApplication.class, args);
    }

}
