package com.mycompany.app.server.auth.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.sql.DataSource;

/**
 * @Description:    //TODO Oauth2 配置文件  认证服务器配置
 *  需要 配置    客户端应用
 *  配置用户    访问认证服务器
 * @Author:         john
 * @CreateDate:     2020/6/24 15:55
 * @Version:        1.0
 */

//@EnableJdbcHttpSession
@Configuration
@EnableAuthorizationServer
public class Oauth2AuthServerConfig extends AuthorizationServerConfigurerAdapter {


    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;
    /**
     * 用户信息校验 验证
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 密码加密类
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public TokenStore tokenStore(){
        //return new JdbcTokenStore(dataSource);
        //改为jwt 模式
        return new JwtTokenStore(jwtTokenEnhancer());
    }



    /**
     * @description:   //TODO  jwt  生成证书: keytool -genkeypair -alias mytest -keyalg RSA -keypass mypass -keystore keystore.jks -storepass mypass
     * @author:        john
     * @return:
     * @exception:
     * @date:          2020/7/19 15:12
     */
    @Bean
    public JwtAccessTokenConverter jwtTokenEnhancer() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        // 导入证书
        KeyStoreKeyFactory keyStoreKeyFactory =
                new KeyStoreKeyFactory(new ClassPathResource("keystore.jks"), "mypass".toCharArray());
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("mytest"));

        return converter;
    }



    /**
     * @description:   //TODO  配置 用户访问
     * @author:        john
     * @param endpoints
     * @return:
     * @exception:
     * @date:          2020/6/24 16:14
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        endpoints.
                // reflesh_token 没有密码，通过userDetailsService 查询用户信息
                userDetailsService(userDetailsService)
                .tokenStore(tokenStore())
                .tokenEnhancer(jwtTokenEnhancer())
                .authenticationManager(authenticationManager);
    }

    /**
     * @description:   //TODO 配置 客户端应用的 用户名 和密码
     * @author:        john
     * @param clients
     * @return:        void
     * @exception:
     * @date:          2020/6/24 16:02
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        clients.jdbc(dataSource);

        /*clients.inMemory()
                .withClient("orderApp")
                .secret(passwordEncoder().encode("123456"))
                //访问权限
                .scopes("read","write")
                //token 有效期
                .accessTokenValiditySeconds(3600)
                //资源服务器ID
                .resourceIds("order-server")
                //授权类型：
                .authorizedGrantTypes("password")
        .and()
                .withClient("orderService")
                .secret(passwordEncoder().encode("123456"))
                //访问权限
                .scopes("read")
                //token 有效期
                .accessTokenValiditySeconds(3600)
                //资源服务器ID
                .resourceIds("order-server")
                //授权类型：
                .authorizedGrantTypes("password");*/
    }


    /**
     * @description:   //TODO 检查 token 访问规则  资源服务器 找认证服务器 验证 token 配置
     * @author:        john
     * @param security
     * @return:
     * @exception:
     * @date:          2020/6/24 20:11
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {

        // 资源服务访问规则
        security.tokenKeyAccess("isAuthenticated()")
                .checkTokenAccess("isAuthenticated()");
    }
}
