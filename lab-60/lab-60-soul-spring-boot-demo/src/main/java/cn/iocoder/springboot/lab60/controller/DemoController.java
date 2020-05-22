package cn.iocoder.springboot.lab60.controller;

import org.dromara.soul.client.common.annotation.SoulClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/get")
    @SoulClient(path = "/demo/get", desc = "示例接口")
    public String get(@RequestParam("id") Integer id) {
        return "DEMO:" + id;
    }

}
