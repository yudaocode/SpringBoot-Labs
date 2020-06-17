package cn.iocoder.springboot.lab65.demo.controller;

import https.github_com.yunaiv.springboot_labs.tree.master.lab_65.lab_65_cxf_ws_demo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private UserService userService;

    @GetMapping("/get")
    public String get(@RequestParam("id") Integer id) {
        UserGetRequest request = new UserGetRequest();
        request.setId(id);
        // 执行 Web Services 请求
        UserGetResponse response = userService.get(request);
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
        UserCreateResponse response = userService.create(request);
        // 响应
        return response.getId();
    }

}
