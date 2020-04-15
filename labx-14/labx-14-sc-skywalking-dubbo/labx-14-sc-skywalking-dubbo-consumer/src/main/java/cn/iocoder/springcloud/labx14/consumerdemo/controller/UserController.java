package cn.iocoder.springcloud.labx14.consumerdemo.controller;

import cn.iocoder.springcloud.labx14.api.UserService;
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
