package com.mycompany.imooc.cr.design.pattern.optimization;

import org.springframework.context.ApplicationEventPublisher;

public class PaymentService {

    private ApplicationEventPublisher applicationEventPublisher;

    public void paid(String orderNumber) {
        OrderPaidEvent orderPaidEvent = new OrderPaidEvent(orderNumber);
        applicationEventPublisher.publishEvent(orderPaidEvent);
    }
}
