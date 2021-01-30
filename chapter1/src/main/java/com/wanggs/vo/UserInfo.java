package com.wanggs.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author: wgs
 * @Date: 2021/1/30
 */
@Data
public class UserInfo {
    private Integer id;
    private String name;
    @NotBlank(message = "用户名不能为空")
    private String userName;
    @NotBlank(message = "密码不能为空")
    private String password;

}
