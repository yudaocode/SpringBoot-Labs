package cn.iocoder.springboot.lab29.asynctask.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class DemoService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Async
    public Integer exception(Integer a, Integer b) {
        throw new RuntimeException("发生了一个可爱的异常");
    }

}
