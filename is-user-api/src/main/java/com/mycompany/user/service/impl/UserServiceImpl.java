package com.mycompany.user.service.impl;

import com.mycompany.user.entity.SysUser;
import com.mycompany.user.mapper.SysUserMapper;
import com.mycompany.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;


/**
 * @author john
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserMapper userMapper;

    @Override
    public SysUser findByUsername(String username) {
        Example example = Example.builder(SysUser.class)
                .where(Sqls.custom().andEqualTo("username",username))
                .build();
        return userMapper.selectOneByExample(example);
    }
}
