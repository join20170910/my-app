package com.mycompany.app.server.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;


/**
 * @Description:    //TODO 资源服务器调用 认证服务器 认证配置
 * @Author:         john
 * @CreateDate:     2020/6/24 22:30
 * @UpdateUser:     john
 * @UpdateDate:     2020/6/24 22:30
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
@Configuration
@EnableWebSecurity
public class Oauth2WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final String TOKEN_CHECK_URL = "http://localhost:9090/oauth/check_token";

    /**
     * @description:   //TODO 远程 调用 认证 token 的服务
     * @author:        john
     * @exception:
     * @date:          2020/6/24 22:33
     */
    @Bean
    public ResourceServerTokenServices tokenServices(){
        RemoteTokenServices tokenServices = new RemoteTokenServices();

        //认证服务器配置的clientid
        tokenServices.setClientId("order-service");
        //认证服务器配置的clientid 对应的密码
        tokenServices.setClientSecret("123456");
        //认证服务器地址
        tokenServices.setCheckTokenEndpointUrl(TOKEN_CHECK_URL);
        return tokenServices;
    }


    /**
     * @description:   //TODO 认证用户信息的bean  资源服务器调用 该方法 验证 token 信息
     * @author:        john
     * @return:
     * @exception:
     * @date:          2020/6/24 22:40
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        OAuth2AuthenticationManager oAuth2AuthenticationManager = new OAuth2AuthenticationManager();
        oAuth2AuthenticationManager.setTokenServices(tokenServices());
        return oAuth2AuthenticationManager;
    }
}
