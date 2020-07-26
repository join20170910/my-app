package com.mycompany.user.controller;

import com.mycompany.common.vo.Result;
import com.mycompany.user.entity.SysRole;
import com.mycompany.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author apple
 * @Description //TODO
 * @Date 00:11 2020/7/26
 * @Param 
 * @return
 **/
@RestController
@RequestMapping("role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @GetMapping("getRoleByUserId/{userId}")
    public Result getRoleByUserId(@PathVariable("userId") Integer userId){
        List<SysRole> roleList = roleService.getRoleByUserId(userId);
        return Result.ok().setData(roleList);
    }

}