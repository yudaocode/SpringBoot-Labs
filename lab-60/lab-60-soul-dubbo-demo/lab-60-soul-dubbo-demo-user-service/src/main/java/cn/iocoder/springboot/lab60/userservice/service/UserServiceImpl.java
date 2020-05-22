package cn.iocoder.springboot.lab60.userservice.service;

import cn.iocoder.springboot.lab60.userservice.api.UserService;
import org.dromara.soul.client.common.annotation.SoulClient;

@org.apache.dubbo.config.annotation.Service(version = "1.0.0")
public class UserServiceImpl implements UserService {

    @Override
    @SoulClient(path = "/user/get", desc = "获得用户详细")
    public String getUser(Integer id) {
        return "User:" + id;
    }

}

