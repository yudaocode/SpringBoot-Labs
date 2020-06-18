package cn.iocoder.springboot.lab67.nettyclientdemo.client;

import cn.iocoder.springboot.lab67.nettycommondemo.codec.InvocationDecoder;
import cn.iocoder.springboot.lab67.nettycommondemo.codec.InvocationEncoder;
import cn.iocoder.springboot.lab67.nettycommondemo.dispacher.MessageDispatcher;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NettyClientHandlerInitializer extends ChannelInitializer<Channel> {

    @Autowired
    private MessageDispatcher messageDispatcher;

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ch.pipeline()
                // 空闲检测
//                .addLast(new ServerIdleStateHandler())
                // 编码器
                .addLast(new InvocationEncoder())
                // 解码器
                .addLast(new InvocationDecoder())
                // 客户端处理器
                .addLast(messageDispatcher)
        ;
    }

}
