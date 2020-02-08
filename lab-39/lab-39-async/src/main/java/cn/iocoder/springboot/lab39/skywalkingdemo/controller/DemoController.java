package cn.iocoder.springboot.lab39.skywalkingdemo.controller;

import cn.iocoder.springboot.lab39.skywalkingdemo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @GetMapping("/async")
    public String echo() {
        demoService.async();
        return "async";
    }

}
