package cn.iocoder.springboot.lab39.skywalkingdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/echo")
    public String echo() {
        return "echo";
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

}
