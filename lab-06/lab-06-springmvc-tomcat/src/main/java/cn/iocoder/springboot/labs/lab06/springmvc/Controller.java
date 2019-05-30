package cn.iocoder.springboot.labs.lab06.springmvc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/hello")
    public String hello() {
//        System.out.println(Thread.currentThread().getName());
        return "world";
    }

    @GetMapping("/sleep")
    public String sleep() throws InterruptedException {
        Thread.sleep(100L);
//        System.out.println(Thread.currentThread().getName());
        return "world";
    }

}
