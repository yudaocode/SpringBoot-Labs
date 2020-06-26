package cn.iocoder.springboot.lab68.resourceserverdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户 Controller
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/info")
    public String hello() {
        return "world";
    }

}
