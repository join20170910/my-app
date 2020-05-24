package com.mycompany.imooc.cr.security.result.optimization;

import java.util.Optional;

public class OrderService {

    public Optional<Order> getOrder(String orderId) {
        // find in db
        return Optional.ofNullable(null);
    }

}
