package com.mycompany.user.controller;

import com.mycompany.common.vo.Result;
import com.mycompany.user.entity.SysUser;
import com.mycompany.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UserController
 * @Deacription TODO
 * @Author apple
 * @Date 2020/7/25 23:35
 * @Version 1.0
 **/
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("findByUsername/{username}")
    public Result findByUsername(@PathVariable("username") String username){
        SysUser sysUser = userService.findByUsername(username);
        if (null == sysUser){
            return Result.failure(100,"用户不存在");
        }
        return Result.ok().setData(sysUser);
    }

}
