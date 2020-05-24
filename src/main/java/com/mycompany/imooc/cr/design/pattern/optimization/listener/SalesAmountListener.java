package com.mycompany.imooc.cr.design.pattern.optimization.listener;

import com.mycompany.imooc.cr.design.pattern.optimization.OrderPaidEvent;
import org.springframework.context.event.EventListener;

public class SalesAmountListener {

    @EventListener
    public void onOrderPaid(OrderPaidEvent event) {
        // 刷新成交总额
    }
}
