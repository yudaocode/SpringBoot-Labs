package cn.iocoder.springboot.lab65.demo.controller;

import cn.iocoder.springboot.lab65.demo.client.UserClient;
import cn.iocoder.springboot.lab65.demo.wsdl.UserCreateResponse;
import cn.iocoder.springboot.lab65.demo.wsdl.UserGetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private UserClient userClient;

    @GetMapping("/get")
    public String get(@RequestParam("id") Integer id) {
        // 执行 Web Services 请求
        UserGetResponse response = userClient.getUser(id);
        // 响应
        return response.getName();
    }

    @GetMapping("/create") // 为了方便测试，实际使用 @PostMapping
    public Integer create(@RequestParam("name") String name,
                          @RequestParam("gender") Integer gender) {
        // 执行 Web Services 请求
        UserCreateResponse response = userClient.createUser(name, gender);
        // 响应
        return response.getId();
    }

}
