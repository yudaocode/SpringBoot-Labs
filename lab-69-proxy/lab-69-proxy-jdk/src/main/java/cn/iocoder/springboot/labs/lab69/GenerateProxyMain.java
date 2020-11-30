package cn.iocoder.springboot.labs.lab69;

import cn.iocoder.springboot.labs.lab69.service.UserServiceImpl;
import org.apache.commons.io.IOUtils;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Proxy;

/**
 * 生成 JDK {@link Proxy} 的示例代码
 *
 * 生成后，我们可以反编译查看具体的类
 */
public class GenerateProxyMain {

    public static void main(String[] args) throws IOException {
        // 生成字节码
        byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy11", UserServiceImpl.class.getInterfaces());
        // 写入到磁盘
        IOUtils.write(classFile, new FileOutputStream("/Users/yunai/ls/$Proxy11.class"));
    }

}
