package cn.iocoder.springcloud.labx14.rabbitmqdemo.consumerdemo.listener;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface MySink {

    String DEMO01_INPUT = "demo01-input";

    @Input(DEMO01_INPUT)
    SubscribableChannel demo01Input();

}
