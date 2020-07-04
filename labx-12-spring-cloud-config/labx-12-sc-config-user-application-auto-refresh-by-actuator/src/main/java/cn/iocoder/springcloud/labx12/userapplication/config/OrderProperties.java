package cn.iocoder.springcloud.labx12.userapplication.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
//@NacosConfigurationProperties(prefix = "order", dataId = "${nacos.config.data-id}", type = ConfigType.YAML)
@ConfigurationProperties(prefix = "order")
public class OrderProperties {

    /**
     * 订单支付超时时长，单位：秒。
     */
    private Integer payTimeoutSeconds;

    /**
     * 订单创建频率，单位：秒
     */
    private Integer createFrequencySeconds;

//    /**
//     * 配置描述
//     */
//    private String desc;

    public Integer getPayTimeoutSeconds() {
        return payTimeoutSeconds;
    }

    public OrderProperties setPayTimeoutSeconds(Integer payTimeoutSeconds) {
        this.payTimeoutSeconds = payTimeoutSeconds;
        return this;
    }

    public Integer getCreateFrequencySeconds() {
        return createFrequencySeconds;
    }

    public OrderProperties setCreateFrequencySeconds(Integer createFrequencySeconds) {
        this.createFrequencySeconds = createFrequencySeconds;
        return this;
    }

//    public String getDesc() {
//        return desc;
//    }
//
//    public OrderProperties setDesc(String desc) {
//        this.desc = desc;
//        return this;
//    }

}
