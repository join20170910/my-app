package com.mycompany.app.bean.discount;

import lombok.Data;
import lombok.ToString;

/**
 * @author john
 */
@Data
@ToString
public class BookOrder {

    //����ԭʼ�۸񣬼��Ż�ǰ�۸�

    private Double originalPrice;

    //������ʵ�۸񣬼��Żݺ�۸�

    private Double realPrice;
}
