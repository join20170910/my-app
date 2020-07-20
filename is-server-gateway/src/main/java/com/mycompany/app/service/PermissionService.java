package com.mycompany.app.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author apple
 * @Description //TODO 权限查询  数据库 redis
 * @Date 21:42 2020/7/20
 * @Param
 * @return
 **/
public interface PermissionService {

    boolean hasPermission(HttpServletRequest request, Authentication authentication);

}
