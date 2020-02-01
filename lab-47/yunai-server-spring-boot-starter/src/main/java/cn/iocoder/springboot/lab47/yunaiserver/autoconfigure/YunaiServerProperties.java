package cn.iocoder.springboot.lab47.yunaiserver.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "yunai.server")
public class YunaiServerProperties {

    /**
     * 默认端口
     */
    private static final Integer DEFAULT_PORT = 8000;

    /**
     * 端口
     */
    private Integer port = DEFAULT_PORT;

    public static Integer getDefaultPort() {
        return DEFAULT_PORT;
    }

    public Integer getPort() {
        return port;
    }

    public YunaiServerProperties setPort(Integer port) {
        this.port = port;
        return this;
    }

}
