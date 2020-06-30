package cn.iocoder.springboot.lab23.testdemo.controller;

import cn.iocoder.springboot.lab23.testdemo.dataobject.UserDO;
import cn.iocoder.springboot.lab23.testdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户 Controller
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获得指定用户编号的用户
     *
     * @param id 用户编号
     * @return 用户
     */
    @GetMapping("/get") // URL 修改成 /get
    public UserDO get(@RequestParam("id") Integer id) {
        // 查询并返回用户
        return userService.get(id);
    }

}
