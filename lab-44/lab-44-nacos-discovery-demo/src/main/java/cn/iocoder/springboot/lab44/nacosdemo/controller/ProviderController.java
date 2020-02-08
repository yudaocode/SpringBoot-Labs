package cn.iocoder.springboot.lab44.nacosdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/provider")
public class ProviderController {

    @GetMapping("/demo")
    public String provider() {
        return "echo";
    }

}
