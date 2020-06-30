package cn.iocoder.springboot.lab68.resourceserverdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Callback02Controller {

    @GetMapping("/callback02")
    public String login() {
        return "假装这里有一个页面";
    }

}
