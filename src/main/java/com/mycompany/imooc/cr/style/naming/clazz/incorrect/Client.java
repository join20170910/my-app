package com.mycompany.imooc.cr.style.naming.clazz.incorrect;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Client {

    public void send(String USER) {
        log.info("send mail by version: {}", mailService.version);
        mailService mailService = new mailService();
        mailService.send(USER);
        log.info("send mail using server: {}", mailService.server);
    }
}
