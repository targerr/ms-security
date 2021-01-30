package com.wanggs.pojo;

import com.wanggs.vo.UserInfo;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @Author: wgs
 * @Date: 2021/1/29
 */
@Data
@Entity
public class User {
    @Id
    private Long id;
    private String name;
    private String userName;
    private String password;

    public UserInfo buildInfo(){
        UserInfo info = new UserInfo();
        BeanUtils.copyProperties(this,info);
        return info;
    }
}
