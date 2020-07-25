package com.mycompany.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.access.ExceptionTranslationFilter;

/**
 * @author john
 *  资源服务器
 */
@Configuration
@EnableResourceServer
public class GatewaySecurityConfig extends ResourceServerConfigurerAdapter {

    @Autowired

    private GatewayWebSecurityExpressionHandler gatewayWebSecurityExpressionHandler;
    @Autowired
    private GatewayAccessDeniedHandler accessDeniedHandler;
    @Autowired
    private GatewayAuthenticationEntryPoint authenticationEntryPoint;
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.
                authenticationEntryPoint(authenticationEntryPoint)
                .expressionHandler(gatewayWebSecurityExpressionHandler)
                   //认证拒绝处理 403
                  .accessDeniedHandler(accessDeniedHandler);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //  ExceptionTranslationFilter  权限 抛出异常 处理
        //  在认证和授权之前记录  开启审核日志   分别 在它们  前和后 记录日志
        http.
                addFilterBefore(new GatewayAuditLogFilter(), ExceptionTranslationFilter.class)
                .authorizeRequests()
                //申请token的请求直接通过不做权限控制
                .antMatchers("/token/**").permitAll()
                .anyRequest().access("#permissionService.hasPermission(request,authentication)")
                .anyRequest().authenticated();
    }
}
