package cn.iocoder.springboot.lab51.sentrydemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/sentry")
    public String sentry() {
        logger.error("[main][我就是展示下异常！]");

        return "success";
    }

    @GetMapping("/exception")
    public String exception() {
        throw new RuntimeException("直接抛出异常");
    }

}
