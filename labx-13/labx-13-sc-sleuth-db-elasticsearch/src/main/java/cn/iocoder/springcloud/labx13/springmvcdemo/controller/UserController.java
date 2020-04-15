package cn.iocoder.springcloud.labx13.springmvcdemo.controller;

import cn.iocoder.springcloud.labx13.springmvcdemo.dataobject.ESUserDO;
import cn.iocoder.springcloud.labx13.springmvcdemo.repository.ESUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ESUserRepository userRepository;

    @GetMapping("/get")
    public String get(@RequestParam("id") Integer id) {
        this.findById(id);
        return "success";
    }

    public ESUserDO findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

}
