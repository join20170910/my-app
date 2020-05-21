package com.mycompany.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author john
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()                         //定义权限配置
                .anyRequest().authenticated()       //任何请求都必须经过认证才能访问
                .and()
                .formLogin()                                    //定制登录表单
                .loginPage("/login")                    //设置登录url-定制登录页面
                .defaultSuccessUrl("/home")       //设置登录成功默认跳转url
                .permitAll()                                  //允许任何人访问登录url
                .and()
                .logout().permitAll();                        //允许任何人访问登出url
    }


}
