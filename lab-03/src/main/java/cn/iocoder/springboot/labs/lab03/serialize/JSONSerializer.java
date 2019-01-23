package cn.iocoder.springboot.labs.lab03.serialize;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class JSONSerializer implements Serializer<Object> {

    public void configure(Map<String, ?> map, boolean b) {

    }

    public byte[] serialize(String topic, Object data) {
        return JSON.toJSONBytes(data);
    }

    public void close() {

    }

}