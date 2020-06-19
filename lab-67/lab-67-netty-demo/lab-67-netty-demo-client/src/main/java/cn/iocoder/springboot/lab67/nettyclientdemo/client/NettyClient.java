package cn.iocoder.springboot.lab67.nettyclientdemo.client;

import cn.iocoder.springboot.lab67.nettyclientdemo.client.handler.NettyClientHandlerInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class NettyClient {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${netty.server.host}")
    private String serverHost;
    @Value("${netty.server.port}")
    private Integer serverPort;

    @Autowired
    private NettyClientHandlerInitializer nettyClientHandlerInitializer;

    /**
     * 线程组，用于客户端对服务端的链接、数据读写
     */
    private EventLoopGroup eventGroup = new NioEventLoopGroup();
    /**
     * Netty Client Channel
     */
    private Channel channel;

    /**
     * 启动 Netty Server
     */
    @PostConstruct
    public void start() throws InterruptedException {
        // 创建 Bootstrap 对象，用于 Netty Client 启动
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventGroup) // 设置一个 EventLoopGroup 对象
                .channel(NioSocketChannel.class)  // 指定 Channel 为客户端 NioSocketChannel
                .remoteAddress(serverHost, serverPort) // 指定链接服务器的地址
                .option(ChannelOption.SO_KEEPALIVE, true) // TCP Keepalive 机制，实现 TCP 层级的心跳保活功能
                .option(ChannelOption.TCP_NODELAY, true) // 允许较小的数据包的发送，降低延迟
                .handler(nettyClientHandlerInitializer);
        // 链接服务器，并同步等待成功，即启动客户端
        ChannelFuture future = bootstrap.connect().sync();
        if (future.isSuccess()) {
            channel = future.channel();
            logger.info("[start][Netty Client 连接服务器({}:{}) 成功]", serverHost, serverPort);
        }
    }

    /**
     * 关闭 Netty Server
     */
    @PreDestroy
    public void shutdown() {
        // 关闭 Netty Client
        if (channel != null) {
            channel.close();
        }
        // 优雅关闭一个 EventLoopGroup 对象
        eventGroup.shutdownGracefully();
    }

}
