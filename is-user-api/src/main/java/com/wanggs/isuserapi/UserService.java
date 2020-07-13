package com.wanggs.isuserapi;

import com.wanggs.isuserapi.pojo.User;

/**
 * @author Wgs
 * @version 1.0
 * @create：2020/07/13
 */
public interface UserService {
    public abstract User findByUsername(String name);
}
