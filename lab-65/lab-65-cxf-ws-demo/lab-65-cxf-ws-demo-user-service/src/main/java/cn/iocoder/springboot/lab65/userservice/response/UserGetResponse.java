package cn.iocoder.springboot.lab65.userservice.response;

/**
 * 获得用户信息 Response
 */
public class UserGetResponse {

    /**
     * 用户编号
     */
    private Integer id;
    /**
     * 昵称
     */
    private String name;
    /**
     * 性别别
     */
    private Integer gender;

    public Integer getId() {
        return id;
    }

    public UserGetResponse setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserGetResponse setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getGender() {
        return gender;
    }

    public UserGetResponse setGender(Integer gender) {
        this.gender = gender;
        return this;
    }
}
