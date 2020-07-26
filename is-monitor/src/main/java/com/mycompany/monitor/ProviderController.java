package com.mycompany.monitor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//配置支持动态更行
@RefreshScope
public class ProviderController {

    @Value("${config.info}")
    private String config;

    @Value("${server.port}")
    private String server_port;

    @GetMapping("sayhello")
    public String getInfo(){
        return "say hello: "+server_port+" config: "+config;
    }
}
