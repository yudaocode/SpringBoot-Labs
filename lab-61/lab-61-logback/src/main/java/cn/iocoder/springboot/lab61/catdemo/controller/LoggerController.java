package cn.iocoder.springboot.lab61.catdemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logger")
public class LoggerController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/error")
    public String error() {
        try {
            int result = 1 / 0;
        } catch (Throwable e) {
            logger.error("计算异常", e);
        }
        return "success";
    }

}
