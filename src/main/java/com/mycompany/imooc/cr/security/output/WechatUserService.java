package com.mycompany.imooc.cr.security.output;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WechatUserService {
    public List<User> search(String username) {
        return new ArrayList<>();
    }
}
