package cn.iocoder.springboot.lab67.nettyserverdemo.server;

import cn.iocoder.springboot.lab67.nettycommondemo.codec.Invocation;
import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class NettyChannelManager {

    private static final AttributeKey<String> CHANNEL_ATTR_KEY_USER = AttributeKey.newInstance("user");

    private Logger logger = LoggerFactory.getLogger(getClass());

    private ConcurrentMap<ChannelId, Channel> channels = new ConcurrentHashMap<>();
    private ConcurrentMap<String, Channel> userChannels = new ConcurrentHashMap<>();

    public void add(Channel channel) {
        channels.put(channel.id(), channel);
        logger.info("[add][一个连接({})加入]", channel.id());
    }

    public void addUser(Channel channel, String user) {
        Channel existChannel = channels.get(channel.id());
        if (existChannel == null) {
            logger.error("[addUser][连接({}) 不存在]", channel.id());
            return;
        }
        // 设置属性
        channel.attr(CHANNEL_ATTR_KEY_USER).set(user);
        // 添加到 userChannels
        userChannels.put(user, channel);
    }

    public void remove(Channel channel) {
        // 移除 channels
        channels.remove(channel.id());
        // 移除 userChannels
        if (channel.hasAttr(CHANNEL_ATTR_KEY_USER)) {
            userChannels.remove(channel.attr(CHANNEL_ATTR_KEY_USER).get());
        }
        logger.info("[remove][一个连接({})离开]", channel.id());
    }

    public void send(String user, Invocation invocation) {
        // 获得用户对应的 Channel
        Channel channel = userChannels.get(user);
        if (channel == null) {
            logger.error("[send][连接不存在]");
            return;
        }
        if (!channel.isActive()) {
            logger.error("[send][连接({})未激活]", channel.id());
            return;
        }
        // 发送消息
        channel.writeAndFlush(invocation);
    }

    public void sendAll(Invocation invocation) {
        for (Channel channel : channels.values()) {
            if (!channel.isActive()) {
                logger.error("[send][连接({})未激活]", channel.id());
                return;
            }
            // 发送消息
            channel.writeAndFlush(invocation);
        }
    }

}
