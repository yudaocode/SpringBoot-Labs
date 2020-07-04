package cn.iocoder.springboot.lab57.userservice.service;

import cn.iocoder.springboot.lab57.userservice.api.UserService;

@org.apache.dubbo.config.annotation.Service(version = "1.0.0")
public class UserServiceImpl implements UserService {

    @Override
    public String getUser(Integer id) {
        return "User:" + id;
    }

}
