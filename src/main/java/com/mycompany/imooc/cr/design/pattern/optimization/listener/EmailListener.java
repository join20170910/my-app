package com.mycompany.imooc.cr.design.pattern.optimization.listener;

import com.mycompany.imooc.cr.design.pattern.optimization.OrderPaidEvent;
import org.springframework.context.event.EventListener;

public class EmailListener {

    @EventListener
    public void onOrderPaid(OrderPaidEvent event) {
        // 发送邮件提醒给运营人员备货
    }
}
