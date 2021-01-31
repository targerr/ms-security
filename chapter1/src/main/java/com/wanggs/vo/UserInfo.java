package com.wanggs.vo;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

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
    // 权限
    private String permissions;

    public boolean hasPermission(String method) {
        boolean result = false;
        if(StringUtils.equalsIgnoreCase("get", method)) {
            result = StringUtils.contains(permissions, "r");
        }else {
            result = StringUtils.contains(permissions, "w");
        }
        return result;
    }
}
