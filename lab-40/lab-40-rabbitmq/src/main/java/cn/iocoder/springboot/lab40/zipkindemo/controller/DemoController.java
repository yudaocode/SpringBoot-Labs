package cn.iocoder.springboot.lab40.zipkindemo.controller;

import cn.iocoder.springboot.lab40.zipkindemo.producer.DemoProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private DemoProducer producer;

    @GetMapping("/rabbitmq")
    public String echo() {
        this.sendMessage(1);
        return "rabbitmq";
    }

    public void sendMessage(Integer id) {
        producer.syncSend(id);
    }

}
