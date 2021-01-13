package cn.iocoder.springboot.labs.lab69;

import cn.iocoder.springboot.labs.lab69.intercept.UserServiceMethodInterceptor;
import cn.iocoder.springboot.labs.lab69.service.UserServiceImpl;
import net.sf.cglib.proxy.Enhancer;

public class TestProxyMain {

    public static void main(String[] args) {
        // 创建 cglib 增强对象
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserServiceImpl.class); // 设置父类
        enhancer.setCallback(new UserServiceMethodInterceptor());
        // 创建代理
        UserServiceImpl userService = (UserServiceImpl) enhancer.create();
        userService.create("yunai", "buzhidao");
    }

}
