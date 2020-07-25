package com.mycompany.auth.service;

import com.mycompany.auth.service.impl.UserServiceImpl;
import com.mycompany.common.vo.Result;
import com.mycompany.common.vo.UserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author john
 */
@FeignClient(name = "mss-upms",fallback = UserServiceImpl.class)
public interface UserService {
    @GetMapping("user/findByUsername/{username}")


    /**
     * @description:   //TODO  根据用户名查询用户信息
     * @author:        john
     * @param username
     * @return:        com.mycompany.vo.Result<com.mycompany.vo.UserVo>
     * @exception:
     * @date:          2020/7/25 10:19
     */
    Result<UserVo> findByUsername(@PathVariable("username") String username);
}
