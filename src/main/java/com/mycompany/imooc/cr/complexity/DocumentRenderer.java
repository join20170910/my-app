package com.mycompany.imooc.cr.complexity;

public class DocumentRenderer {

    public String render(Document document) {
        String content;
        if ("text".equals(document.getType())) {
            content = renderText(document);
        } else if ("word".equals(document.getType())) {
            content = renderWord(document);
        } else if ("excel".equals(document.getType())) {
            content = renderExcel(document);
        } else {
            throw new UnsupportedOperationException("unsupport document type");
        }
        return content;
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
