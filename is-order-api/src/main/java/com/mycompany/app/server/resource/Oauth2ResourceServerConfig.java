package com.mycompany.app.server.resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;


/**
 * @Description:    //TODO 配置资源服务器
 * @Author:         john
 * @CreateDate:     2020/6/24 21:31
 * @UpdateUser:     john
 * @UpdateDate:     2020/6/24 21:31
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
@Configuration
@EnableResourceServer
public class Oauth2ResourceServerConfig extends ResourceServerConfigurerAdapter {


    /**
     * @description:   //TODO 申明 资源服务ID
     * @author:        john
     * @param resources
     * @return:
     * @exception:
     * @date:          2020/6/24 21:34
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {

        //资源服务器ID
        resources.resourceId("order-server");
    }
}
