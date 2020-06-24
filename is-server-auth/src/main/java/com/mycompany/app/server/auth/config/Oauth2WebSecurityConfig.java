package com.mycompany.app.server.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * @Description:    //TODO web 应用完全配置
 * @Author:         john
 * @CreateDate:     2020/6/24 16:23
 * @UpdateUser:     john
 * @UpdateDate:     2020/6/24 16:23
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
@Configuration
@EnableWebSecurity
public class Oauth2WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 获取 用户信息
     */
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * @Description:    //TODO 构建 AuthenticationManager对象
     * Oauth2AuthServerConfig 使用
     * 需要   UserDetailsService    PasswordEncoder
     * @Author:         john
     * @CreateDate:     2020/6/24 16:41
     * @UpdateUser:     john
     * @UpdateDate:     2020/6/24 16:41
     * @UpdateRemark:   修改内容
     * @Version:        1.0
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 获取 用户信息 比对 密码
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }


    /**
     * @Description:    //TODO 构建 AuthenticationManager Bean
     * @Author:         john
     * @CreateDate:     2020/6/24 17:03
     * @Version:        1.0
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
