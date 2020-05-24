package com.mycompany.imooc.cr.security.result;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PaymentService {

    private final OrderService orderService;

    public Payment getOrderPaymentInfo(String orderId) {
        Order order = orderService.getOrder(orderId);
        String paymentNum = order.getPaymentNum();
        return getPaymentInfo(paymentNum);
    }

    private Payment getPaymentInfo(String paymentNum) {
        return new Payment();
    }
}
