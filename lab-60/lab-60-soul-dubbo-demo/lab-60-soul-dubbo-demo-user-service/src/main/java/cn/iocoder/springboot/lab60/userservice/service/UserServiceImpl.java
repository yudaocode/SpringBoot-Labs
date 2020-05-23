package cn.iocoder.springboot.lab60.userservice.service;

import cn.iocoder.springboot.lab60.userservice.api.UserService;
import cn.iocoder.springboot.lab60.userservice.api.dto.UserCreateDTO;
import org.dromara.soul.client.common.annotation.SoulClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@org.apache.dubbo.config.annotation.Service(version = "1.0.0")
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    @SoulClient(path = "/user/get", desc = "获得用户详细")
    public String getUser(Integer id) {
        return "User:" + id;
    }

    @Override
    @SoulClient(path = "/user/create", desc = "创建用户")
    public Integer createUser(UserCreateDTO createDTO) {
        logger.info("[createUser][username({}) password({})]", createDTO.getNickname(), createDTO.getGender());
        return 1;
    }

}

