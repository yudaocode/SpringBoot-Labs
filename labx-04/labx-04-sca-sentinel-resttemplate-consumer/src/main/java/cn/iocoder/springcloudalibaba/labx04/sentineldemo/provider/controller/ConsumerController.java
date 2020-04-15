package cn.iocoder.springcloudalibaba.labx04.sentineldemo.provider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/echo")
    public String echo() {
        return restTemplate.getForObject("http://127.0.0.1:8080/demo/echo", String.class);
    }

}
