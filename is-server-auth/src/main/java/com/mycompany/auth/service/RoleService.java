package com.mycompany.auth.service;

import com.mycompany.auth.service.impl.RoleServiceImpl;
import com.mycompany.common.vo.Result;
import com.mycompany.common.vo.RoleVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author john
 */
@FeignClient(name = "mss-upms",fallback = RoleServiceImpl.class)
public interface RoleService {
    @GetMapping("role/getRoleByUserId/{userId}")
    Result<List<RoleVo>> getRoleByUserId(@PathVariable("userId") Integer userId);
}
