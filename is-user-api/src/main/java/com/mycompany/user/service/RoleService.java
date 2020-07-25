package com.mycompany.user.service;

import com.mycompany.user.entity.SysRole;

import java.util.List;
/**
 * @author john
 */
public interface RoleService {

    List<SysRole> getRoleByUserId(Integer userId);
}
