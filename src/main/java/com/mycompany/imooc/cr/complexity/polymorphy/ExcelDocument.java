package com.mycompany.imooc.cr.complexity.polymorphy;

public class ExcelDocument implements Document {
    @Override
    public String getType() {
        return "excel";
    }

    @Override
    public String render() {
        return "excel content";
    }
}
