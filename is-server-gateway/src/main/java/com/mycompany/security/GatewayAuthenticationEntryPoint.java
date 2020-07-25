package com.mycompany.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.http.AccessTokenRequiredException;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName GatewayAuthenticationEntryPoint
 * @Deacription TODO 401 默认处理
 * @Author apple
 * @Date 2020/7/20 22:34
 * @Version 1.0
 **/
@Component
@Slf4j
public class GatewayAuthenticationEntryPoint extends OAuth2AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        // 没有传 token
        if (authException instanceof AccessTokenRequiredException){

            logger.error("2. update log to 401");
            // token 有问题
        }else{
            logger.error("2. add log 401");
        }

        request.setAttribute("logUpdated","yes");
        super.commence(request, response, authException);
    }
}
