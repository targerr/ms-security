package com.wanggs.service.impl;

import com.wanggs.pojo.User;
import com.wanggs.repository.UserRepository;
import com.wanggs.service.UserService;
import com.wanggs.vo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: wgs
 * @Date: 2021/1/30
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public User findByUsername(String name) {
        return userRepository.findByUserName(name);
    }

    @Override
    public UserInfo findById(Long id) {
        return  userRepository.findById(id).get().buildInfo();
    }

    @Override
    public UserInfo save(UserInfo user) {
        return null;
    }
}
