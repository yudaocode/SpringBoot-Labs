package cn.iocoder.springboot.lab67.nettycommondemo.codec;

import cn.iocoder.springboot.lab67.nettycommondemo.dispacher.Message;
import com.alibaba.fastjson.JSON;

public class Invocation {

    /**
     * 类型
     */
    private String type;
    /**
     * 消息
     */
    private String message;

    public Invocation() {
    }

    public Invocation(String type, Message message) {
        this.type = type;
        this.message = JSON.toJSONString(message);
    }

    public String getType() {
        return type;
    }

    public Invocation setType(String type) {
        this.type = type;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Invocation setMessage(String message) {
        this.message = message;
        return this;
    }

}
