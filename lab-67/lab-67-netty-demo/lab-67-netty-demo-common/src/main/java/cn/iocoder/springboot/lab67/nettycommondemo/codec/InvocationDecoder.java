package cn.iocoder.springboot.lab67.nettycommondemo.codec;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.CorruptedFrameException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * {@link Invocation} 解码器
 */
public class InvocationDecoder extends ByteToMessageDecoder {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        // 标记当前读取位置
        in.markReaderIndex();
        // 判断是否能够读取 length 长度
        if (in.readableBytes() <= 4) {
            return;
        }
        // 读取长度
        int length = in.readInt();
        if (length < 0) {
            throw new CorruptedFrameException("negative length: " + length);
        }
        // 如果 message 不够可读，则退回到原读取位置
        if (in.readableBytes() < length) {
            in.resetReaderIndex();
            return;
        }
        // 读取内容
        byte[] content = new byte[length];
        in.readBytes(content);
        // 解析成 Invocation
        Invocation invocation = JSON.parseObject(content, Invocation.class);
        out.add(invocation);
        logger.info("[decode][连接({}) 解析到一条消息({})]", ctx.channel().id(), invocation.toString());
    }

}
