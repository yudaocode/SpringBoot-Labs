package cn.iocoder.springboot.lab40.zipkindemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/springmvc")
    public String echo() {
        return "springmvc";
    }

}
