package cn.iocoder.springboot.lab65.userservice.request;

/**
 * 创建用户信息 Request
 */
public class UserCreateRequest {

    /**
     * 昵称
     */
    private String name;
    /**
     * 性别别
     */
    private Integer gender;

    public String getName() {
        return name;
    }

    public UserCreateRequest setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getGender() {
        return gender;
    }

    public UserCreateRequest setGender(Integer gender) {
        this.gender = gender;
        return this;
    }

}
