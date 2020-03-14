package cn.iocoder.springcloud.labx12.userapplication.controller;

import cn.iocoder.springcloud.labx12.userapplication.config.OrderProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/demo")
@RefreshScope
public class DemoController {

    @Autowired
    private OrderProperties orderProperties;

    /**
     * 测试 @ConfigurationProperties 注解的配置属性类
     */
    @GetMapping("/test01")
    public OrderProperties test01() {
        return orderProperties;
    }

    @Value(value = "${order.pay-timeout-seconds}") // @NacosValue(value = "${order.pay-timeout-seconds}")
    private Integer payTimeoutSeconds;
    @Value(value = "${order.create-frequency-seconds}") // @NacosValue(value = "${order.create-frequency-seconds}")
    private Integer createFrequencySeconds;

    /**
     * 测试 @Value 注解的属性
     */
    @GetMapping("/test02")
    public Map<String, Object> test02() {
        Map<String, Object> result = new HashMap<>();
        result.put("payTimeoutSeconds", payTimeoutSeconds);
        result.put("createFrequencySeconds", createFrequencySeconds);
        return result;
    }

    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/logger")
    public void logger() {
        logger.debug("[logger][测试一下]");
    }

}
