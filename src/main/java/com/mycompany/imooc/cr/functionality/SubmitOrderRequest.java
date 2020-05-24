package com.mycompany.imooc.cr.functionality;

import java.util.List;

public class SubmitOrderRequest {

    private List<ProductItem> products;

    private String deliveryInfoId;


    public static class ProductItem {
        private String productId;

        private int count;

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

    public List<ProductItem> getProducts() {
        return products;
    }

    public void setProducts(List<ProductItem> products) {
        this.products = products;
    }

    public String getDeliveryInfoId() {
        return deliveryInfoId;
    }

    public void setDeliveryInfoId(String deliveryInfoId) {
        this.deliveryInfoId = deliveryInfoId;
    }
}
