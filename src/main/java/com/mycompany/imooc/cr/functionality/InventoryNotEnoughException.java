package com.mycompany.imooc.cr.functionality;

public class InventoryNotEnoughException extends RuntimeException {
    public InventoryNotEnoughException(String msg) {
        super(msg);
    }
}
