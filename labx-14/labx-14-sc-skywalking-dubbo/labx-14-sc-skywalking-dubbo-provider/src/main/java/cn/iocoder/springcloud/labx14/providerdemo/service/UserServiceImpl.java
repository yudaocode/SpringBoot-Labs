package cn.iocoder.springcloud.labx14.providerdemo.service;


import cn.iocoder.springcloud.labx14.api.UserService;

@org.apache.dubbo.config.annotation.Service(protocol = "dubbo", version = "1.0.0")
public class UserServiceImpl implements UserService {

    @Override
    public String get(Integer id) {
        return "user:" + id;
    }

}
