package com.mycompany.user.service;


import com.mycompany.user.entity.SysMenu;

import java.util.List;

/**
 * @author john
 */
public interface PermissionService {


    /**
     * @description:   //TODO  角色编号 找权限
     * @author:        john
     * @param roleId
     * @return:
     * @exception:
     * @date:          2020/7/25 10:58
     */
    List<SysMenu> getPermissionsByRoleId(Integer roleId);
}
