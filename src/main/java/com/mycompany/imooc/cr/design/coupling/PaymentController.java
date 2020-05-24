package com.mycompany.imooc.cr.design.coupling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/payments")
@RestController
public class PaymentController {

    @Autowired
    private AliPayClient aliPayClient;

    /**
     * 获取订单的支付签名
     *
     * @param orderNumber 订单编号
     * @return 签名字符串
     */
    @RequestMapping("/{orderNumber}/signature")
    public String getPaySignature(@PathVariable String orderNumber) {
        return aliPayClient.getPaySignature();
    }
}
