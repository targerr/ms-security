package com.wanggs.security.service.impl;

import com.wanggs.security.service.PermissionService;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: wgs
 * @Date: 2021/2/9
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        //查数据库、查redis、调远程服务、或者内存里面的权限信息
        System.err.println(request.getRequestURI());
        System.err.println(ReflectionToStringBuilder.toString(authentication));
        //这里模拟
        return RandomUtils.nextInt() % 2 ==0;
    }
}
