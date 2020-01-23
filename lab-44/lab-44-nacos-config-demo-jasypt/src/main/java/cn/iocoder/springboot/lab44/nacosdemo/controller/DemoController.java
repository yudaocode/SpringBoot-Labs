package cn.iocoder.springboot.lab44.nacosdemo.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

//    @Value("${spring.application.name}")
    @NacosValue(value = "${spring.application.name}", autoRefreshed = true)
    private String applicationName;

    @GetMapping("/test")
    public String test() {
        return applicationName;
    }

}
