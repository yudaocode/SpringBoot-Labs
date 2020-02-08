package cn.iocoder.springboot.lab45.apollodemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private Environment environment;

    @GetMapping("/test")
    public void test() {
        System.out.println(environment);
    }

}
