package com.mycompany.app.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName GatewayAccessDeniedHandler
 * @Deacription TODO  权限 拒绝处理  使用 默认处理器
 * @Author apple
 * @Date 2020/7/20 22:18
 * @Version 1.0
 **/
@Component
@Slf4j
public class GatewayAccessDeniedHandler extends OAuth2AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException authException) throws IOException, ServletException {
        logger.info("2. update log to 403");
        request.setAttribute("logUpdated","yes");
        super.handle(request, response, authException);
    }
}
