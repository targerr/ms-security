package com.wanggs.repository;

import com.wanggs.pojo.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author: wgs
 * @Date: 2021/1/30
 */
public interface UserRepository extends JpaSpecificationExecutor<User>, CrudRepository<User, Long> {

    List<User> findByName(String username);

    User findByUserName(String name);
}
