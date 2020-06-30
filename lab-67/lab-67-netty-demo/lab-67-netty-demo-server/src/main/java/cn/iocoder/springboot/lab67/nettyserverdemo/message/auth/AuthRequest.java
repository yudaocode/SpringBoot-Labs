package cn.iocoder.springboot.lab67.nettyserverdemo.message.auth;

import cn.iocoder.springboot.lab67.nettycommondemo.dispatcher.Message;

/**
 * 用户认证请求
 */
public class AuthRequest implements Message {

    public static final String TYPE = "AUTH_REQUEST";

    /**
     * 认证 Token
     */
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public AuthRequest setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    @Override
    public String toString() {
        return "AuthRequest{" +
                "accessToken='" + accessToken + '\'' +
                '}';
    }

}
