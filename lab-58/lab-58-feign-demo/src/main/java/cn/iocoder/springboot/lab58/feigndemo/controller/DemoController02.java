package cn.iocoder.springboot.lab58.feigndemo.controller;

import cn.iocoder.springboot.lab58.feigndemo.feign.UserServiceFeignClient02;
import cn.iocoder.springboot.lab58.feigndemo.feign.request.UserAddRequest;
import cn.iocoder.springboot.lab58.feigndemo.feign.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/demo02")
public class DemoController02 {

    @Autowired
    private UserServiceFeignClient02 userServiceFeignClient;

    @GetMapping("/test01")
    public UserResponse test01() {
        return userServiceFeignClient.get(1);
//        System.out.println("编号：" + user.getId());
//        System.out.println("昵称：" + user.getName());
//        System.out.println("性别：" + user.getGender());
    }

    @GetMapping("/test02A")
    public List<UserResponse> test02A() {
        return userServiceFeignClient.list("你猜", null);
    }

//    @GetMapping("/test02B")
//    public List<UserResponse> test02B() {
//        Map<String, Object> queryMap = new HashMap<>();
//        queryMap.put("name", "昵称");
//        return userServiceFeignClient.list(queryMap);
//    }

    @GetMapping("/test03")
    public Integer test03() {
        return userServiceFeignClient.add(new UserAddRequest()
            .setName("昵称").setGender(1));
    }

}
