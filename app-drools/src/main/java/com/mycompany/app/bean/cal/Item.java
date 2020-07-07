package com.mycompany.app.bean.cal;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Item {
    private Integer id;
    //订单编码

    private Integer orderId;
    //商品编码

    private String goodId;

    //商品名称

    private String name;
    //单价

    private Double price;
    //数量

    private Double count;

    private Order order;
    //getter、setter略

    public Item(Integer id,Integer orderId,Double price){
        this.id = id;
        this.orderId = orderId;
        this.price = price;
    }

}
