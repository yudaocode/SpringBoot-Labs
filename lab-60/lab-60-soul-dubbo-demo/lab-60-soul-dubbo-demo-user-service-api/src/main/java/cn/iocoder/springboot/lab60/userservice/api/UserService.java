package cn.iocoder.springboot.lab60.userservice.api;

import cn.iocoder.springboot.lab60.userservice.api.dto.UserCreateDTO;

public interface UserService {

    String getUser(Integer id);

    Integer createUser(UserCreateDTO createDTO);

}
