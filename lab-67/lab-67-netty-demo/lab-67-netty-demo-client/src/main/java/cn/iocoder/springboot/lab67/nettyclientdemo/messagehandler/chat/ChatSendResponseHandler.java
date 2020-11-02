package cn.iocoder.springboot.lab67.nettyclientdemo.messagehandler.chat;

import cn.iocoder.springboot.lab67.nettyclientdemo.message.chat.ChatSendResponse;
import cn.iocoder.springboot.lab67.nettycommondemo.dispatcher.MessageHandler;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ChatSendResponseHandler implements MessageHandler<ChatSendResponse> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void execute(Channel channel, ChatSendResponse message) {
        logger.info("[execute][发送结果：{}]", message);
    }

    @Override
    public String getType() {
        return ChatSendResponse.TYPE;
    }

}
