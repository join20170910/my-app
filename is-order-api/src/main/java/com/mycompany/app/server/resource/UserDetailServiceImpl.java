package com.mycompany.app.server.resource;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


/**
 * @Description:    //TODO 构建 user 对象
 * @Author:         john
 * @CreateDate:     2020/6/25 20:55
 * @Version:        1.0
 */
@Component
public class UserDetailServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = new User();
        user.setId(1L);
        user.setUsername(username);
        return user;
    }
}
