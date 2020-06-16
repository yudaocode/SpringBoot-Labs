package cn.iocoder.springboot.lab65.userservice.request;

/**
 * 获得用户信息 Request
 */
public class UserGetRequest {

    /**
     * 用户编号
     */
    private Integer id;

    public Integer getId() {
        return id;
    }

    public UserGetRequest setId(Integer id) {
        this.id = id;
        return this;
    }

}
