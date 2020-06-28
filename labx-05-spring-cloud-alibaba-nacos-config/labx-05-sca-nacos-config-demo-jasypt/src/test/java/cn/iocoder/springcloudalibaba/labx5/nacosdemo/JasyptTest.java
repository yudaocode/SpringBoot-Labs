package cn.iocoder.springcloudalibaba.labx5.nacosdemo;

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
        String password = "woshimima";
        System.out.println(encryptor.encrypt(password));

        // 第二个加密
        password = "bushimima";
        System.out.println(encryptor.encrypt(password));
    }

    @Value("${xxx-password:}")
    private String xxxPassword;

    @Test
    public void print() {
        System.out.println(xxxPassword);
    }

//    @Value("${jasypt.encryptor.password}")
//    private String password;

}
