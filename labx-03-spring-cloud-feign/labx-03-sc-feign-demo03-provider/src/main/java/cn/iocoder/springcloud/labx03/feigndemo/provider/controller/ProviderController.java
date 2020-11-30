package cn.iocoder.springcloud.labx03.feigndemo.provider.controller;

import cn.iocoder.springcloud.labx03.feigndemo.provider.api.ProviderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController implements ProviderService {

    private Logger logger = LoggerFactory.getLogger(ProviderController.class);

    @Value("${server.port}")
    private Integer serverPort;

    @Override
    public String echo(String name) {
        // 模拟执行 100ms 时长。方便后续我们测试请求超时
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // 记录被调用的日志
        logger.info("[echo][被调用啦 name({})]", name);

        return serverPort + "-provider:" + name;
    }

//    @GetMapping("/echo")
//    public String echo(String name) throws InterruptedException {
//        // 模拟执行 100ms 时长。方便后续我们测试请求超时
//        Thread.sleep(100L);
//
//        // 记录被调用的日志
//        logger.info("[echo][被调用啦 name({})]", name);
//
//        return serverPort + "-provider:" + name;
//    }

}
