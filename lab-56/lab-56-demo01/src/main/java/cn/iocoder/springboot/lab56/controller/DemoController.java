package cn.iocoder.springboot.lab56.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Value("${server.port}")
    private Integer serverPort;

    @GetMapping("/echo")
    public String echo() {
        return "echo:" + serverPort;
    }

}
