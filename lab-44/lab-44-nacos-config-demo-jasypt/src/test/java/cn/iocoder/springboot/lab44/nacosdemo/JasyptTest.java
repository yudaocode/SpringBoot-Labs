package cn.iocoder.springboot.lab44.nacosdemo;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JasyptTest {

    @Autowired
    private StringEncryptor encryptor;

    @Test
    public void encode() {
        // 第一个加密
        String applicationName = "demo-application";
        System.out.println(encryptor.encrypt(applicationName));

//        // 第二个加密
//        applicationName = "demo-app";
//        System.out.println(encryptor.encrypt(applicationName));
    }

    @Value("${spring.application.name}")
//    @NacosValue("${spring.application.name}")
    private String applicationName;

    @Test
    public void print() {
        System.out.println(applicationName);
    }

    @Value("${jasypt.encryptor.password}")
    private String password;

}
