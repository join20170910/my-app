package com.mycompany.app.bean.discount;

import lombok.Data;
import lombok.ToString;

/**
 * @author john
 */
@Data
@ToString
public class BookOrder {

    //订单原始价格，即优惠前价格

    private Double originalPrice;

    //订单真实价格，即优惠后价格

    private Double realPrice;
}
