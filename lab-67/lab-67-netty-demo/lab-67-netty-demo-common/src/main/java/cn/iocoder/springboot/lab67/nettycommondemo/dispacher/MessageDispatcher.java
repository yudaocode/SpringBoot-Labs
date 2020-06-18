package cn.iocoder.springboot.lab67.nettycommondemo.dispacher;

import cn.iocoder.springboot.lab67.nettycommondemo.codec.Invocation;
import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.beans.factory.annotation.Autowired;

@ChannelHandler.Sharable
public class MessageDispatcher extends SimpleChannelInboundHandler<Invocation> {

    @Autowired
    private MessageHandlerContainer messageHandlerContainer;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Invocation invocation) {
        // 获得 type 对应的 MessageHandler 处理器
        MessageHandler messageHandler = messageHandlerContainer.getMessageHandler(invocation.getType());
        // 解析消息
        Class<? extends Message> messageClass = MessageHandlerContainer.getMessageClass(messageHandler);
        Message message = JSON.parseObject(invocation.getMessage(), messageClass);
        // 执行逻辑
        // noinspection unchecked
        messageHandler.execute(ctx.channel(), message);
    }

}
