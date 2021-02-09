package com.wanggs.security.service.impl;

import com.wanggs.security.service.PermissionService;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.http.AccessTokenRequiredException;
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

        //如果是已认证的Authentication就是OAuth2Authentication
        //如果是没认证的Authentication就是AnonymousAuthenticationToken
        if(authentication instanceof AnonymousAuthenticationToken){
            //到这里说明没传令牌
            throw new AccessTokenRequiredException(null);
        }
        //这里模拟
//        return RandomUtils.nextInt() % 2 ==0;
        return true;
    }
}
