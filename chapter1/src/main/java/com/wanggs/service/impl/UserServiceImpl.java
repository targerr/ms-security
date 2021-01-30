package com.wanggs.service.impl;

import com.lambdaworks.crypto.SCryptUtil;
import com.wanggs.pojo.User;
import com.wanggs.repository.UserRepository;
import com.wanggs.service.UserService;
import com.wanggs.vo.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: wgs
 * @Date: 2021/1/30
 */
@Service
@Slf4j
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
    public UserInfo save(UserInfo info) {
        User user = new User();
        BeanUtils.copyProperties(info,user);
        String saltPassword = SCryptUtil.scrypt(user.getPassword(),32768,8,1);
        log.info("加密密码: " + saltPassword);
        user.setPassword(saltPassword);
        userRepository.save(user);
        return info;
    }
}
