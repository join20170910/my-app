package com.mycompany.imooc.cr.complexity;

public class Printer {

    public void print(Document document) {
        String content = new DocumentRenderer().render(document);
        doPrint(content);
    }

    private void doPrint(String content) {

    }

    private String renderExcel(Document document) {
        return "excel";
    }

    private String renderWord(Document document) {
        return "word";
    }

    private String renderText(Document document) {
        return "text";
    }

}
