package cn.iocoder.springboot.lab12.mybatis.service;

import cn.iocoder.springboot.lab12.mybatis.dataobject.UserDO;
import cn.iocoder.springboot.lab12.mybatis.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.Future;

@Service
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    @Resource
    private UserMapper userMapper;

    @Async
    public Future<UserDO> getUserAsync(Integer id) {
        UserDO userDO = userMapper.selectById(id);
        log.info("[getUserAsync][id({}) user({})]", id, userDO);
        return AsyncResult.forValue(userDO);
    }

}
