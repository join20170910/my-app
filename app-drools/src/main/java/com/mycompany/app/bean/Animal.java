package com.mycompany.app.bean;

import lombok.Data;

import java.util.List;

/**
 *
 * @author Youdmeng
 * @date 2020/1/7 0007
 */
@Data
public class Animal {

    private Integer level;

    private List<People> peoples;

}
