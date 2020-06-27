package cn.iocoder.springboot.lab68.resourceserverdemo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 示例 Controller
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/admin-list")
    @PreAuthorize("hasRole('ADMIN')") // 要求管理员 ROLE_ADMIN 角色
    public String adminList() {
        return "管理员列表";
    }

    @GetMapping("/user-list")
    @PreAuthorize("hasRole('USER')") // 要求普通用户 ROLE_USER 角色
    public String userList() {
        return "用户列表";
    }

}
