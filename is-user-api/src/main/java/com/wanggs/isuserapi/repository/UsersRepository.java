package com.wanggs.isuserapi.repository;

import com.wanggs.isuserapi.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Wgs
 * @version 1.0
 * @create：2020/07/13
 */
public interface UsersRepository  extends JpaRepository<User, Long> {

    User findByName(String username);

}
