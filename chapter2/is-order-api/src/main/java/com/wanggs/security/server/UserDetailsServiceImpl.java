package com.wanggs.security.server;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @Author: wgs
 * @Date: 2021/1/31
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //这里就不去读数据库了
        User user = new User();
        user.setId(1L);
        user.setUsername(username);
        return user;
    }
}
