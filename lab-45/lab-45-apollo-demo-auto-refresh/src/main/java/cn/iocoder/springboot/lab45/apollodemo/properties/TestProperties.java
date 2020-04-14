package cn.iocoder.springboot.lab45.apollodemo.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "test")
public class TestProperties {

    /**
     * 测试属性
     */
    private String test;

    public String getTest() {
        return test;
    }

    public TestProperties setTest(String test) {
        this.test = test;
        return this;
    }

}
