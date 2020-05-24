package com.mycompany.imooc.cr.complexity.polymorphy;

public class WordDocument implements Document {
    @Override
    public String getType() {
        return "word";
    }

    @Override
    public String render() {
        return "word content";
    }
}
