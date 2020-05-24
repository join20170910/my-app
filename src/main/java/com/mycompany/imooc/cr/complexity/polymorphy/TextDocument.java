package com.mycompany.imooc.cr.complexity.polymorphy;

public class TextDocument implements Document {
    @Override
    public String getType() {
        return "text";
    }

    @Override
    public String render() {
        return "text content";
    }
}
