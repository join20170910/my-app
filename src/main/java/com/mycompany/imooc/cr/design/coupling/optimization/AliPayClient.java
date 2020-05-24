package com.mycompany.imooc.cr.design.coupling.optimization;

import org.springframework.stereotype.Component;

@Component
public class AliPayClient implements PayClient {
    @Override
    public String getPaySignature() {
        return "ali pay signature";
    }
}
