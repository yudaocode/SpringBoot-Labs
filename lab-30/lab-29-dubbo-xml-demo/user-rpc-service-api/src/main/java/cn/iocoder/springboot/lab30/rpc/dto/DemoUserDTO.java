package cn.iocoder.springboot.lab30.rpc.dto;

public class DemoUserDTO {

    /**
     * 用户编号
     */
    private Integer id;
    /**
     * 昵称
     */
    private String name;
    /**
     * 性别
     */
    private Integer gender;

    public Integer getId() {
        return id;
    }

    public DemoUserDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public DemoUserDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getGender() {
        return gender;
    }

    public DemoUserDTO setGender(Integer gender) {
        this.gender = gender;
        return this;
    }
}
