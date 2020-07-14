package com.wanggs.isuserapi.impl;

import com.wanggs.isuserapi.UserService;
import com.wanggs.isuserapi.pojo.User;
import com.wanggs.isuserapi.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Wgs
 * @version 1.0
 * @create：2020/07/13
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public User findByUsername(String name) {
        return usersRepository.findByName(name);
    }

}
