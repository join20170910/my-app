package com.mycompany.security;

import com.mycompany.user.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

/**
 * @ClassName GatewayWebSecurityExpressionHandler
 * @Deacription TODO  解析自定义 权限 标签 服务
 * @Author apple
 * @Date 2020/7/20 21:51
 * @Version 1.0
 **/

@Component
public class GatewayWebSecurityExpressionHandler extends OAuth2WebSecurityExpressionHandler {

    @Autowired
    private PermissionService permissionService;
    @Override
    protected StandardEvaluationContext createEvaluationContextInternal(Authentication authentication, FilterInvocation invocation) {
        StandardEvaluationContext sec =  super.createEvaluationContextInternal(authentication, invocation);
        sec.setVariable("permissionService",permissionService);
       return sec;
    }
}
