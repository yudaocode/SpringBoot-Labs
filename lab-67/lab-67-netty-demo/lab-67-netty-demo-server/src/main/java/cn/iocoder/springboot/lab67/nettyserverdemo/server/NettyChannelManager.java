package cn.iocoder.springboot.lab67.nettyserverdemo.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class NettyChannelManager {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private ConcurrentMap<ChannelId, Channel> channels = new ConcurrentHashMap<>();

    public void add(Channel channel) {
        channels.put(channel.id(), channel);
        logger.info("[add][一个连接({})加入]", channel.id());
    }

    public void remove(Channel channel) {
        channels.remove(channel.id());
        logger.info("[remove][一个连接({})离开]", channel.id());
    }

}
