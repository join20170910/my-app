package com.mycompany.imooc.cr.security.authority;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PutMapping("/users/{userId}")
    @PreAuthorize("hasRole('user') || hasRole('admin')")
    public void update(@RequestBody UpdateUserRequest updateUserRequest, @PathVariable String userId) {
        userService.update(userId, updateUserRequest);
    }


    @PutMapping("/users/{userId}")
    @PreAuthorize("hasRole('user')")
    public void updateBySelf(@RequestBody UpdateUserRequest updateUserRequest, @PathVariable String userId) {
        if (!getCurrentLoginUserId().equals(userId)) {
            throw new IllegalArgumentException("不能更新其他用户");
        }
        userService.update(userId, updateUserRequest);
    }


    @PutMapping("/admin/users/{userId}")
    @PreAuthorize("hasRole('admin')")
    public void updateByAdmin(@RequestBody UpdateUserRequest updateUserRequest, @PathVariable String userId) {
        userService.update(userId, updateUserRequest);
    }

    private String getCurrentLoginUserId() {
        return "current user";
    }

}
