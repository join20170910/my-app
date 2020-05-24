package com.mycompany.imooc.cr.security.output;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class WechatUserController {

    private final WechatUserService wechatUserService;

    public WechatUserController(WechatUserService wechatUserService) {
        this.wechatUserService = wechatUserService;
    }

    @GetMapping
    public List<User> search(@RequestParam("username") String username) {
        return wechatUserService.search(username);
    }
}
