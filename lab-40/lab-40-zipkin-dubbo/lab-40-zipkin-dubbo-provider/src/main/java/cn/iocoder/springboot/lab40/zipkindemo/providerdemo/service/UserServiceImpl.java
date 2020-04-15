package cn.iocoder.springboot.lab40.zipkindemo.providerdemo.service;

import cn.iocoder.springboot.lab40.zipkindemo.api.UserService;

@org.apache.dubbo.config.annotation.Service(version = "1.0.0")
public class UserServiceImpl implements UserService {

    @Override
    public String get(Integer id) {
        return "user:" + id;
    }

}
