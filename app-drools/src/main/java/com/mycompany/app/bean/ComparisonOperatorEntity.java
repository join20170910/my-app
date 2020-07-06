package com.mycompany.app.bean;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author john
 */

@Data
@ToString
public class ComparisonOperatorEntity {

    private String names;
    private List<String> list;
    
}
