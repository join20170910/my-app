package com.mycompany.imooc.cr.complexity.polymorphy;

public class Printer {

    public void print(Document document) {
        String content = document.render();
        doPrint(content);
    }

    private void doPrint(String content) {

    }
}
