package cn.iocoder.springboot.labs.lab69.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class UserServiceHandler implements InvocationHandler {

    /**
     * 被代理的对象
     */
    private final Object object;

    public UserServiceHandler(Object object) {
        this.object = object;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before invoke");
        Object ret = method.invoke(object, args);
        System.out.println("after invoke");
        return ret;
    }

}
