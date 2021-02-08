package com.wanggs.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wgs
 * @Date: 2021/2/7
 */
@RestController
@RequestMapping("/")
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        return "hello spring boot!";
    }
}
