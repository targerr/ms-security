package com.wanggs.controller;

import com.wanggs.constast.CookieConstant;
import com.wanggs.enums.ResultEnum;
import com.wanggs.exception.ResultException;
import com.wanggs.pojo.User;
import com.wanggs.repository.UserRepository;
import com.wanggs.service.UserService;
import com.wanggs.vo.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @Author: wgs
 * @Date: 2021/1/29
 */
@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private UserService userService;

    @GetMapping("/query")
    public List query(String name) {
        String sql = "select * from user where name = '" + name + "'";
        List data = jdbcTemplate.queryForList(sql);
        return data;
    }

    /**
     * http://127.0.0.1:8080/users?name=' or 1=1 or name ='
     * 返回: []
     */


    @GetMapping
    public List<UserInfo> queryList(String name) {
        List<UserInfo> userInfos = userService.findByName(name).
                stream().
                map(e -> {
                    UserInfo userInfo = new UserInfo();
                    BeanUtils.copyProperties(e, userInfo);
                    return userInfo;
                }).collect(Collectors.toList());

        return userInfos;
    }

    @PostMapping("create")
    public UserInfo create(@RequestBody @Validated UserInfo user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("参数不正确, createForm={}", user);
            throw new ResultException(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }

        return userService.save(user);
    }

    @RequestMapping("/{id}")
    public User getById(@PathVariable Long id, HttpServletRequest request) {
        // 从 BasicAuthecationFilter 获取
        User user = (User) request.getAttribute("user");
        if (user == null) {
            throw new RuntimeException("身份信息认证失败!");
        }
        if (!user.getId().equals(id)) {
            throw new RuntimeException("身份信息认证失败");
        }
        return user;
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public UserInfo login(Long id, HttpServletRequest request) {
        UserInfo user = userService.findById(id);
        //若存在会话则返回该会话，否则返回NULL
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        //request.getSession(true)：若存在会话则返回该会话，否则新建一个会话。
        request.getSession(true).setAttribute(CookieConstant.TOKEN, user);
        return user;
    }

    @GetMapping("/getInfo")
    public UserInfo getInfo(HttpServletRequest request) {
        UserInfo user = (UserInfo) request.getSession().getAttribute(CookieConstant.TOKEN);
        return user;
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest request) {
        request.getSession().invalidate();
    }




}
