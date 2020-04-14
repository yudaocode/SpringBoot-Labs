package cn.iocoder.springboot.lab40.zipkindemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/demo")
public class DemoController {

    private final RestTemplate restTemplate;

    DemoController(@Autowired RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @GetMapping("/echo")
    public String echo() {
        return "echo";
    }

    @GetMapping("/http")
    public String http() {
//        restTemplate.getForObject("https://www.baidu.com", String.class);
        restTemplate.getForObject("http://127.0.0.1:8079/demo/echo", String.class);
        return "echo";
    }

}
