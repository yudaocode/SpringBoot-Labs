package cn.iocoder.springboot.lab39.skywalkingdemo.consumerdemo.controller;

import cn.iocoder.springboot.lab39.skywalkingdemo.api.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Reference(protocol = "dubbo", version = "1.0.0")
    private UserService userService;

    @GetMapping("/get")
    public String  get(@RequestParam("id") Integer id) {
        return userService.get(id);
    }

}
