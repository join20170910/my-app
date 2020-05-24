package com.mycompany.imooc.cr.design.coupling;

import org.springframework.stereotype.Component;

@Component
public class AliPayClient {

    public String getPaySignature() {
        return "ali pay signature";
    }
}
