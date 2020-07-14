package com.wanggs.isuserapi.controller;

import com.wanggs.isuserapi.UserService;
import com.wanggs.isuserapi.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Wgs
 * @version 1.0
 * @create：2020/07/13
 */
@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private UserService userService;

    // sql 注入 http://127.0.0.1:8081/findByName?name='or true or'' and name='
    @RequestMapping("/findByName")
    public List getByName(@RequestParam(value = "name", defaultValue = "1") String name) {
        String sql = "SELECT * FROM user WHERE name = '" + name + "'";
        List list = jdbcTemplate.queryForList(sql);
        // sql 注入解决方案
        // String sql = "SELECT * FROM user WHERE name = ?";
        // List<User> list = jdbcTemplate.query(sql, new Object[]{name}, new BeanPropertyRowMapper(User.class));
        return list;
    }

    @RequestMapping("/findName")
    public User getName(@RequestParam(value = "name", defaultValue = "1") String name) {
        User user = userService.findByUsername(name);
        return user;
    }

    @RequestMapping("/{id}")
    public User getById(@PathVariable Long id, HttpServletRequest request) {
        // 从 BasicAuthecationFilter 获取
        User user = (User) request.getAttribute("user");
        if (user == null ) {
            throw new RuntimeException("身份信息认证失败!");
        }
        if ( !user.getId().equals(id)){
            throw new RuntimeException("身份信息认证失败");
        }
        return user;
    }
}
