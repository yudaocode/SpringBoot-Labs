package cn.iocoder.springboot.lab49.lombokdemo.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserService {

    public static void staticMethod() {
        log.info("静态方法示例");
    }

    public void normalMethod() {
        log.info("普通方法示例");
    }

}
