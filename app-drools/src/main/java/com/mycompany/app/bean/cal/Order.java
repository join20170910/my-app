package com.mycompany.app.bean.cal;

import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@ToString
public class Order {
    private Integer id;
    private Integer customerId;
    private Double total;
    private Double discount;
    private Double pay;
    private Date createDate;
    private List<Item> items;
 
}
