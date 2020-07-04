package cn.iocoder.springboot.lab67.nettyclientdemo.messagehandler.chat;

import cn.iocoder.springboot.lab67.nettyclientdemo.message.chat.ChatRedirectToUserRequest;
import cn.iocoder.springboot.lab67.nettycommondemo.dispatcher.MessageHandler;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ChatRedirectToUserRequestHandler implements MessageHandler<ChatRedirectToUserRequest> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void execute(Channel channel, ChatRedirectToUserRequest message) {
        logger.info("[execute][收到消息：{}]", message);
    }

    @Override
    public String getType() {
        return ChatRedirectToUserRequest.TYPE;
    }

}
