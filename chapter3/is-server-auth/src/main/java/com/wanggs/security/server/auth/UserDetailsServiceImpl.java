
package com.wanggs.security.server.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 图示
     * ![image.png](https://upload-images.jianshu.io/upload_images/4994935-bca65dbef25559ab.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return User.withUsername(username)
                .password(passwordEncoder.encode("abc123"))
                .authorities("ROLE_ADMIN") //权限
                .build();//构建一个User对象
    }

}
