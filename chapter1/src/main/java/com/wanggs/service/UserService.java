package com.wanggs.service;

import com.wanggs.pojo.User;
import com.wanggs.vo.UserInfo;

import java.util.List;

/**
 * @Author: wgs
 * @Date: 2021/1/30
 */
public interface UserService {
    public List<User> findByName(String name);

    public User findByUsername(String name);

    public UserInfo findById(Long id) ;

    UserInfo save(UserInfo user);
}
