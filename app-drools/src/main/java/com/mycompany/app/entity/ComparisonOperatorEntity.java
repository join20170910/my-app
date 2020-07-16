package com.mycompany.app.entity;

import lombok.ToString;

import java.util.List;

/**
 * 用于测试比较操作符
 * @author john
 */
@ToString
public class ComparisonOperatorEntity {
    private String names;
    private List<String> list;

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
