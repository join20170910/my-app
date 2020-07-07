package com.mycompany.app.controller;
import com.mycompany.app.service.ProviderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
/**
 * @ClassName ConsumerController
 * @Deacription TODO
 * @Author apple
 * @Date 2020/7/2 21:56
 * @Version 1.0
 **/
@RestController
public class ConsumerController {

    @Resource
    ProviderService providerService;

    @GetMapping("sayhi")
    public String getInfo(){
        return "远程调用: "+providerService.getInfo();
    }
}

