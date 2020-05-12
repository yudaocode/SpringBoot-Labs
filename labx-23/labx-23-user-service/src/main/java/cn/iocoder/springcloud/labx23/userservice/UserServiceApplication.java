package cn.iocoder.springcloud.labx23.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class UserServiceApplication {

    @RestController
    @RequestMapping("/user")
    public class UserController {

        @GetMapping("/get")
        public String get(@RequestParam("id") Integer id) {
            return "User:" + id;
        }

    }

    public static void main(String[] args) {
        // 设置端口
        System.setProperty("server.port", "18080");

        // 应用启动
        SpringApplication.run(UserServiceApplication.class, args);
    }

}
