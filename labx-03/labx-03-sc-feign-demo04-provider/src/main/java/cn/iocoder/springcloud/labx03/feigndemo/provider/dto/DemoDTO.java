package cn.iocoder.springcloud.labx03.feigndemo.provider.dto;

public class DemoDTO {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public DemoDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public DemoDTO setPassword(String password) {
        this.password = password;
        return this;
    }

}
