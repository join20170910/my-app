package com.mycompany.imooc.cr.design.pattern.optimization.listener;

import com.mycompany.imooc.cr.design.pattern.optimization.OrderPaidEvent;
import org.springframework.context.event.EventListener;

public class SmsListener {

    @EventListener
    public void onOrderPaid(OrderPaidEvent event) {
        // 发送短信通知用户
    }
}
