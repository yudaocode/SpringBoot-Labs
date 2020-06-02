package cn.iocoder.springcloud.labx23.hystrixdemo.controller;

import cn.iocoder.springcloud.labx23.hystrixdemo.service.CollapserDemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/collapser-demo")
public class CollapserDemoController {

    private Logger logger = LoggerFactory.getLogger(CollapserDemoController.class);

    @Autowired
    private CollapserDemoService collapserDemoService;

    @GetMapping("/test")
    public void test() throws ExecutionException, InterruptedException {
        logger.info("[test][准备获取用户信息]");
        Future<String> user01 = collapserDemoService.getUserFuture(1);
        Future<String> user02 = collapserDemoService.getUserFuture(2);
        logger.info("[test][提交获取用户信息]");

        logger.info("[test][user({}) 的结果为({})]", 1, user01.get());
        logger.info("[test][user({}) 的结果为({})]", 2, user02.get());
    }


    @GetMapping("/test_02")
    public void test02() throws ExecutionException, InterruptedException {
        logger.info("[test][准备获取用户信息]");
        Future<String> user01 = collapserDemoService.getUserFuture(2);
        Future<String> user02 = collapserDemoService.getUserFuture(1);
        logger.info("[test][提交获取用户信息]");

        logger.info("[test][user({}) 的结果为({})]", 1, user01.get());
        logger.info("[test][user({}) 的结果为({})]", 2, user02.get());
    }

}
