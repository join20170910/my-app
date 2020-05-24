package com.mycompany.imooc.cr.security.result.optimization;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PaymentService {

    private final OrderService orderService;

    public Payment getOrderPaymentInfo(String orderId) {
        return orderService.getOrder(orderId).map(o -> {
            String paymentNum = o.getPaymentNum();
            return getPaymentInfo(paymentNum);
        }).orElseThrow(() -> new IllegalArgumentException("未找到对应订单"));

    }

    private Payment getPaymentInfo(String paymentNum) {
        return new Payment();
    }
}
