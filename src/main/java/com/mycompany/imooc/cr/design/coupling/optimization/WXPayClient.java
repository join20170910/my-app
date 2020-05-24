package com.mycompany.imooc.cr.design.coupling.optimization;

public class WXPayClient implements PayClient {
    @Override
    public String getPaySignature() {
        return "wx pay signature";
    }
}
