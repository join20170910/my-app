package com.mycompany.user.service;

import com.mycompany.entity.SysUser;

/**
 * @author john
 */
public interface UserService {


    /**
     * @description:   //TODO 查询用户
     * @author:        john
     * @param username
     * @return:
     * @exception:
     * @date:          2020/7/24 16:38
     */
    SysUser findByUsername(String username);
}
