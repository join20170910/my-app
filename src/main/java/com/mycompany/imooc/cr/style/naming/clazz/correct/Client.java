package com.mycompany.imooc.cr.style.naming.clazz.correct;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Client {

    public void send(String user) {
        log.info("send mail by version: {}", MailService.VERSION);
        MailService mailService = new MailService();
        mailService.send(user);
        log.info("send mail using server: {}", mailService.server);
    }
}
