package cn.iocoder.springcloud.labx29.publisherdemo.controller;

import cn.iocoder.springcloud.labx29.publisherdemo.event.UserRegisterEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.bus.ServiceMatcher;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private ServiceMatcher busServiceMatcher;

    @GetMapping("/register")
    public String register(String username) {
        // ... 执行注册逻辑
        logger.info("[register][执行用户({}) 的注册逻辑]", username);

        // ... 发布
        applicationEventPublisher.publishEvent(new UserRegisterEvent(this, busServiceMatcher.getServiceId(),
                null, username));
        return "success";
    }

}
