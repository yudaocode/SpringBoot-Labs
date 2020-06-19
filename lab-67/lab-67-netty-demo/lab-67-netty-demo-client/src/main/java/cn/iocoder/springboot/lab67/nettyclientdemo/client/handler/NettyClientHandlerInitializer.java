package cn.iocoder.springboot.lab67.nettyclientdemo.client.handler;

import cn.iocoder.springboot.lab67.nettycommondemo.codec.InvocationDecoder;
import cn.iocoder.springboot.lab67.nettycommondemo.codec.InvocationEncoder;
import cn.iocoder.springboot.lab67.nettycommondemo.dispacher.MessageDispatcher;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NettyClientHandlerInitializer extends ChannelInitializer<Channel> {

    /**
     * 心跳超时时间
     */
    private static final Integer WRITE_TIMEOUT_SECONDS = 3;

    @Autowired
    private MessageDispatcher messageDispatcher;

    @Autowired
    private NettyClientHandler nettyClientHandler;

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ch.pipeline()
                // 空闲检测
                .addLast(new IdleStateHandler(WRITE_TIMEOUT_SECONDS, 0, 0))
                // 编码器
                .addLast(new InvocationEncoder())
                // 解码器
                .addLast(new InvocationDecoder())
                // 客户端处理器
                .addLast(messageDispatcher)
                // 客户端处理器
                .addLast(nettyClientHandler)
        ;
    }

}
