//package com.wanggs.security.server;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//
///**
// * @Author: wgs
// * @Date: 2021/1/31
// */
//public class User implements UserDetails {
//    private Long id;
//
//    private String username;
//
//    private String password;
//
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN");
//    }
//
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;  //账号没过期
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;//账号没被锁定
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;//密码没过期
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;//是否可用
//    }
//
//
//    @Override
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", username='" + username + '\'' +
//                ", password='" + password + '\'' +
//                '}';
//    }
//}
