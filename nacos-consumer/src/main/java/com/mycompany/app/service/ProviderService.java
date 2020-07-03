package com.mycompany.app.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("nacos-config")
public interface ProviderService {

    @GetMapping("sayhello")
    public String getInfo();
}
