package com.mycompany.app.bean;

import lombok.Data;

/**
 * @author john
 */
@Data
public class People {
    private int sex;

    private String name;

    private Integer age;

    private String drlType;

    public People(int sex, String name, Integer age, String drlType) {
        this.sex = sex;
        this.name = name;
        this.age = age;
        this.drlType = drlType;
    }

    public People(int sex, String name, String drlType) {
        this.sex = sex;
        this.name = name;
        this.drlType = drlType;
    }

    public People() {
    }

}
