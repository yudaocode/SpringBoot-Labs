package cn.iocoder.springboot.lab65.userservice.response;

/**
 * 创建用户信息 Response
 */
public class UserCreateResponse {

    /**
     * 用户编号
     */
    private Integer id;

    public Integer getId() {
        return id;
    }

    public UserCreateResponse setId(Integer id) {
        this.id = id;
        return this;
    }

}
