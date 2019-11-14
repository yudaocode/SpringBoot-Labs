package cn.iocoder.springboot.lab21.cache.service;

import cn.iocoder.springboot.lab21.cache.dataobject.UserDO;
import cn.iocoder.springboot.lab21.cache.mapper.UserCacheDao;
import cn.iocoder.springboot.lab21.cache.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserCacheDao userCacheDao;

    public UserDO getUser(Integer id) {
        // 从 Cache 中，查询用户信息
        UserDO user = userCacheDao.get(id);
        if (user != null) {
            return user;
        }
        // 如果 Cache 查询不到，从 DB 中读取
        user = userMapper.selectById(id);
        if (user != null) { // 非空，则缓存到 Cache 中
            userCacheDao.put(user);
        }
        // 返回结果
        return user;
    }

    public UserDO getUser2(Integer id) {
        return userMapper.selectById(id);
    }

}
