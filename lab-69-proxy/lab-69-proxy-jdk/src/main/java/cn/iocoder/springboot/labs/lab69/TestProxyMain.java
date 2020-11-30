package cn.iocoder.springboot.labs.lab69;

import cn.iocoder.springboot.labs.lab69.handler.UserServiceHandler;
import cn.iocoder.springboot.labs.lab69.service.UserService;
import cn.iocoder.springboot.labs.lab69.service.UserServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 使用 JDK {@link Proxy} 实现一个动态代理的示例
 */
public class TestProxyMain {

    public static void main(String[] args) {
        // 创建 UserService 对象
        UserService userService = new UserServiceImpl();
        // JDK 处理器
        InvocationHandler handler = new UserServiceHandler(userService);
        // 创建代理
        UserService userServiceProxy = (UserService) Proxy.newProxyInstance(TestProxyMain.class.getClassLoader(), userService.getClass().getInterfaces(), handler);
        // 执行
        userServiceProxy.create("yunai", "buzhidao");
    }

}
