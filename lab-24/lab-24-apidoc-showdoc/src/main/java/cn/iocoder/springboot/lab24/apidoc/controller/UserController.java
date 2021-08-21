package cn.iocoder.springboot.lab24.apidoc.controller;

import cn.iocoder.springboot.lab24.apidoc.controller.vo.UserLoginReqVO;
import cn.iocoder.springboot.lab24.apidoc.controller.vo.UserLoginRespVO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping("/login")
    public UserLoginRespVO login(@RequestBody UserLoginReqVO reqVO) {
        UserLoginRespVO respVO = new UserLoginRespVO();
        respVO.setUserId(1024);
        respVO.setUsername(reqVO.getUsername());
        respVO.setName("芋道源码");
        return respVO;
    }

    /**
     * showdoc
     * @title 用户登录
     * @description 用户登录的接口
     * @url http://127.0.0.1:8080/user/login2
     * @method POST
     * @json_param {"username": "yudaoyuanma", "password": "yunai"}
     * @param username 必选 string 用户名
     * @param password 必选 string 密码
     * @return { "userId": 1024, "name": "芋道源码", "username": "yudaoyuanma" }
     * @return_param userId 必选 number 用户编号
     * @return_param name 必选 string 用户昵称
     * @return_param username 必选 string 用户账号
     * @remark 我就是快乐的备注
     */
    @PostMapping("/login2")
    public UserLoginRespVO login2(@RequestBody UserLoginReqVO reqVO) {
        UserLoginRespVO respVO = new UserLoginRespVO();
        respVO.setUserId(1024);
        respVO.setUsername(reqVO.getUsername());
        respVO.setName("芋道源码");
        return respVO;
    }

}
