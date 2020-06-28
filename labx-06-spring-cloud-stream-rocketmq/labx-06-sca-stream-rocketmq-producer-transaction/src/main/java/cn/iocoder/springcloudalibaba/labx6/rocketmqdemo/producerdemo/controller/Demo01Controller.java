package cn.iocoder.springcloudalibaba.labx6.rocketmqdemo.producerdemo.controller;

import cn.iocoder.springcloudalibaba.labx6.rocketmqdemo.producerdemo.message.Demo01Message;
import cn.iocoder.springcloudalibaba.labx6.rocketmqdemo.producerdemo.message.MySource;
import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.common.message.MessageConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/demo01")
public class Demo01Controller {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MySource mySource;

    @GetMapping("/send")
    public boolean send() {
        // 创建 Message
        Demo01Message message = new Demo01Message()
                .setId(new Random().nextInt());
        // 创建 Spring Message 对象
        Message<Demo01Message> springMessage = MessageBuilder.withPayload(message)
                .build();
        // 发送消息
        return mySource.demo01Output().send(springMessage);
    }

    @GetMapping("/send_delay")
    public boolean sendDelay() {
        // 创建 Message
        Demo01Message message = new Demo01Message()
                .setId(new Random().nextInt());
        // 创建 Spring Message 对象
        Message<Demo01Message> springMessage = MessageBuilder.withPayload(message)
                .setHeader(MessageConst.PROPERTY_DELAY_TIME_LEVEL, "3") // 设置延迟级别为 3，10 秒后消费。
                .build();
        // 发送消息
        boolean sendResult = mySource.demo01Output().send(springMessage);
        logger.info("[sendDelay][发送消息完成, 结果 = {}]", sendResult);
        return sendResult;
    }

    @GetMapping("/send_tag")
    public boolean sendTag() {
        for (String tag : new String[]{"yunai", "yutou", "tudou"}) {
            // 创建 Message
            Demo01Message message = new Demo01Message()
                    .setId(new Random().nextInt());
            // 创建 Spring Message 对象
            Message<Demo01Message> springMessage = MessageBuilder.withPayload(message)
                    .setHeader(MessageConst.PROPERTY_TAGS, tag) // 设置 Tag
                    .build();
            // 发送消息
            mySource.demo01Output().send(springMessage);
        }
        return true;
    }

    @GetMapping("/send_transaction")
    public boolean sendTransaction() {
        // 创建 Message
        Demo01Message message = new Demo01Message()
                .setId(new Random().nextInt());
        // 创建 Spring Message 对象
        Args args = new Args().setArgs1(1).setArgs2("2");
        Message<Demo01Message> springMessage = MessageBuilder.withPayload(message)
                .setHeader("args", JSON.toJSONString(args))
                .build();
        // 发送消息
        return mySource.demo01Output().send(springMessage);
    }

    public static class Args {

        private Integer args1;

        private String args2;

        public Integer getArgs1() {
            return args1;
        }

        public Args setArgs1(Integer args1) {
            this.args1 = args1;
            return this;
        }

        public String getArgs2() {
            return args2;
        }

        public Args setArgs2(String args2) {
            this.args2 = args2;
            return this;
        }

        @Override
        public String toString() {
            return "Args{" +
                    "args1=" + args1 +
                    ", args2='" + args2 + '\'' +
                    '}';
        }

    }

}
