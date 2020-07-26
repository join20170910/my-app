package com.mycompany.user.controller;

import com.mycompany.common.vo.Result;
import com.mycompany.user.entity.SysMenu;
import com.mycompany.user.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author apple
 * @Description //TODO
 * @Date 23:57 2020/7/25
 * @Param
 * @return
 **/
@Slf4j
@RestController
@RequestMapping("permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;
    @GetMapping("getRolePermission/{roleId}")
    public Result getRolePermission(@PathVariable("roleId") Integer roleId){
        List<SysMenu> menuList = permissionService.getPermissionsByRoleId(roleId);
        return Result.ok().setData(menuList);
    }

}