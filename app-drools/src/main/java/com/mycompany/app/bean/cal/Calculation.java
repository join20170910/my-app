package com.mycompany.app.bean.cal;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author john
 */

@Data
@ToString
public class Calculation implements Serializable {

    private double wage;//税前工资

    private double wagemore;//应纳税所得额

    private double cess;//税率

    private double preminus;//速算扣除数

    private double wageminus;//扣税额

    private double actualwage;//税后工资

}
