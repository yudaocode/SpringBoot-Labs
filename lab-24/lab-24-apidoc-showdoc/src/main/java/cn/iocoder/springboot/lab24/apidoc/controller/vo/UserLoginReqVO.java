package cn.iocoder.springboot.lab24.apidoc.controller.vo;

public class UserLoginReqVO {

    /**
     * 用户名
     */
    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public UserLoginReqVO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserLoginReqVO setPassword(String password) {
        this.password = password;
        return this;
    }
}
