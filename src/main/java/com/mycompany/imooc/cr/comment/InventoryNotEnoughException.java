package com.mycompany.imooc.cr.comment;

public class InventoryNotEnoughException extends RuntimeException {
    public InventoryNotEnoughException(String msg) {
        super(msg);
    }
}
