package cn.iocoder.springcloud.labx23.userservice.service;

import cn.iocoder.springcloud.labx23.userservice.api.UserService;

@org.apache.dubbo.config.annotation.Service(protocol = "dubbo", version = "1.0.0")
public class UserServiceImpl implements UserService {

    @Override
    public String getUser(Integer id) {
        return "User:" + id;
    }

}
