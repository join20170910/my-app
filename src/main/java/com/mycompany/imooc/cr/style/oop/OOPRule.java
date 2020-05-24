package com.mycompany.imooc.cr.style.oop;

public class OOPRule {

    void getUser() {
        UserService userService = new UserService();
        userService.getUsers();

        int version = userService.VERSION;

        version = UserService.VERSION;
    }
}
