package com.mycompany.imooc.cr.security.result.optimization;

import lombok.Getter;

import java.util.List;

@Getter
public class Order {
    private List<OrderItem> orderItems;

    private String phone;

    private String address;

    private String concatName;

    private String paymentNum;

    public Order(List<OrderItem> orderItems, String phone, String address, String concatName) {
        this.orderItems = orderItems;
        this.phone = phone;
        this.address = address;
        this.concatName = concatName;
    }

    public static class OrderItem {
        private String productId;
        private int count;

        public OrderItem(String productId, int count) {
            this.productId = productId;
            this.count = count;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
