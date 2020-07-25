package com.mycompany.user.service.impl;

import com.mycompany.user.entity.SysMenu;
import com.mycompany.user.mapper.SysMenuMapper;
import com.mycompany.user.mapper.SysMenuMapper;
import com.mycompany.user.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private SysMenuMapper menuMapper;
    @Override
    public List<SysMenu> getPermissionsByRoleId(Integer roleId) {
        return menuMapper.getPermissionsByRoleId(roleId);
    }
}
