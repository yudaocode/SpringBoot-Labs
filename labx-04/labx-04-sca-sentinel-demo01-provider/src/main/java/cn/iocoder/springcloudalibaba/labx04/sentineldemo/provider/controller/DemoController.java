package cn.iocoder.springcloudalibaba.labx04.sentineldemo.provider.controller;

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

    @GetMapping("/test")
    public String test() {
        return "test";
    }

}
