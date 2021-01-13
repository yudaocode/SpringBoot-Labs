package cn.iocoder.springboot.labs.lab69.intercept;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class UserServiceMethodInterceptor implements MethodInterceptor {

    public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("before invoke");
        Object ret = methodProxy.invokeSuper(object, args);
        System.out.println("after invoke");
        return ret;
    }

}
