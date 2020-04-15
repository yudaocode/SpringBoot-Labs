package cn.iocoder.springboot.labx13.activemqdemo.controller;

import cn.iocoder.springboot.labx13.activemqdemo.producer.DemoProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private DemoProducer producer;

    @GetMapping("/activemq")
    public String echo() {
        this.sendMessage(1);
        return "activemq";
    }

    public void sendMessage(Integer id) {
        producer.syncSend(id);
    }

}
