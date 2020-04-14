package cn.iocoder.springboot.lab39.skywalkingdemo.controller;

import cn.iocoder.springboot.lab39.skywalkingdemo.producer.DemoProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private DemoProducer producer;

    @GetMapping("/kafka")
    public String echo() throws ExecutionException, InterruptedException {
        this.sendMessage(1);
        return "kafka";
    }

    public void sendMessage(Integer id) throws ExecutionException, InterruptedException {
        producer.syncSend(id);
    }

}
