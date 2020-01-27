package cn.iocoder.springboot.lab45.apollodemo.controller;

import cn.iocoder.springboot.lab45.apollodemo.properties.TestProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Value("${test.test}")
    private String test;

    @GetMapping("/test")
    public String test() {
        return test;
    }

    @Autowired
    private TestProperties testProperties;

    @GetMapping("/test_properties")
    public TestProperties testProperties() {
        return testProperties;
    }

    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/logger")
    public void logger() {
        logger.debug("[logger][测试一下]");
    }

}
