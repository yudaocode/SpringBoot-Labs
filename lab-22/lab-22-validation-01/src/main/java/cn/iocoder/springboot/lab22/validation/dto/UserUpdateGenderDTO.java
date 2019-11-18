package cn.iocoder.springboot.lab22.validation.dto;

import cn.iocoder.springboot.lab22.validation.constants.GenderEnum;
import cn.iocoder.springboot.lab22.validation.core.validator.InEnum;

import javax.validation.constraints.NotNull;

/**
 * 用户更新性别 DTO
 */
public class UserUpdateGenderDTO {

    /**
     * 用户编号
     */
    @NotNull(message = "用户编号不能为空")
    private Integer id;

    /**
     * 性别
     */
    @NotNull(message = "性别不能为空")
    @InEnum(value = GenderEnum.class, message = "性别必须是 {value}")
    private Integer gender;

    public Integer getId() {
        return id;
    }

    public UserUpdateGenderDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getGender() {
        return gender;
    }

    public UserUpdateGenderDTO setGender(Integer gender) {
        this.gender = gender;
        return this;
    }

    @Override
    public String toString() {
        return "UserUpdateGenderDTO{" +
                "id=" + id +
                ", gender=" + gender +
                '}';
    }

}
