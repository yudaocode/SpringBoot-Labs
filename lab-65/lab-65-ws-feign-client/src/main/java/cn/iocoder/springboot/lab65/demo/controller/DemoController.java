package cn.iocoder.springboot.lab65.demo.controller;

import cn.iocoder.springboot.lab65.demo.feign.UserServiceFeignClient;
import cn.iocoder.springboot.lab65.demo.wsdl.UserCreateRequest;
import cn.iocoder.springboot.lab65.demo.wsdl.UserCreateResponse;
import cn.iocoder.springboot.lab65.demo.wsdl.UserGetRequest;
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
    private UserServiceFeignClient userClient;

    @GetMapping("/get")
    public String get(@RequestParam("id") Integer id) {
        // 请求
        UserGetRequest request = new UserGetRequest();
        request.setId(id);
        // 执行 Web Services 请求
        UserGetResponse response = userClient.getUser(request);
        // 响应
        return response.getName();
    }

    @GetMapping("/create") // 为了方便测试，实际使用 @PostMapping
    public Integer create(@RequestParam("name") String name,
                          @RequestParam("gender") Integer gender) {
        // 请求
        UserCreateRequest request = new UserCreateRequest();
        request.setName(name);
        request.setGender(gender);
        // 执行 Web Services 请求
        UserCreateResponse response = userClient.createUser(request);
        // 响应
        return response.getId();
    }

}
