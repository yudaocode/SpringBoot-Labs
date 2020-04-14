package cn.iocoder.springcloud.labx09.apollodemo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Value("${xxx-password:}")
    private String xxxPassword;

    @GetMapping("/test")
    public String test() {
        return xxxPassword;
    }

}
