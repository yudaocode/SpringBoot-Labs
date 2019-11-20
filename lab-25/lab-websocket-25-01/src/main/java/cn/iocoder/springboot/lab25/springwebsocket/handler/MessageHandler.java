package cn.iocoder.springboot.lab25.springwebsocket.handler;

import cn.iocoder.springboot.lab25.springwebsocket.message.Message;

import javax.websocket.Session;

/**
 * 消息处理器
 */
public interface MessageHandler<T extends Message> {

    void execute(Session session, T message);

    String getType();

}
