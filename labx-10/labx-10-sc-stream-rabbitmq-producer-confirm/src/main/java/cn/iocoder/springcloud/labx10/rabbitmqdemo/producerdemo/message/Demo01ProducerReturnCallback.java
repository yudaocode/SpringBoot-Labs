package cn.iocoder.springcloud.labx10.rabbitmqdemo.producerdemo.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.context.IntegrationContextUtils;
import org.springframework.messaging.support.ErrorMessage;
import org.springframework.stereotype.Component;

@Component
public class Demo01ProducerReturnCallback {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @ServiceActivator(inputChannel = "DEMO-TOPIC-01.errors")
    public void handleError(ErrorMessage errorMessage) {
        logger.error("[handleError][headers：{}]", errorMessage.getHeaders());
        logger.error("[handleError][payload：{}]", errorMessage.getPayload().getMessage());
        logger.error("[handleError][originalMessage：{}]", errorMessage.getOriginalMessage());
    }

    @StreamListener(IntegrationContextUtils.ERROR_CHANNEL_BEAN_NAME) // errorChannel
    public void globalHandleError(ErrorMessage errorMessage) {
        logger.error("[globalHandleError][payload：{}]", errorMessage.getPayload().getMessage());
        logger.error("[globalHandleError][originalMessage：{}]", errorMessage.getOriginalMessage());
        logger.error("[globalHandleError][headers：{}]", errorMessage.getHeaders());
    }

}
