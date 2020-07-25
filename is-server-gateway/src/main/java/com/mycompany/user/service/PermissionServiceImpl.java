package com.mycompany.user.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.http.AccessTokenRequiredException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName PermissionServiceImpl
 * @Deacription TODO
 * @Author apple
 * @Date 2020/7/20 21:43
 * @Version 1.0
 **/

@Service
@Slf4j
public class PermissionServiceImpl implements PermissionService{

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {

        log.info(request.getRequestURI());
        log.info(ReflectionToStringBuilder.toString(authentication));

        //是否为 匿名用户
        if(authentication instanceof AnonymousAuthenticationToken){
             throw new AccessTokenRequiredException(null);
        }

        return RandomUtils.nextInt() %2 == 0;
    }
}
