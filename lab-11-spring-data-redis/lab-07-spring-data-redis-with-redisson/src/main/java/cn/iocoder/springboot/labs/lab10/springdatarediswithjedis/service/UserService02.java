package cn.iocoder.springboot.labs.lab10.springdatarediswithjedis.service;

import cn.iocoder.springboot.labs.lab10.springdatarediswithjedis.cacheobject.UserCacheObject;
import cn.iocoder.springboot.labs.lab10.springdatarediswithjedis.dao.redis.UserCacheDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService02 {

    @Autowired
    private UserCacheDao userCacheDao;

    public UserCacheObject get(Integer id) {
        return userCacheDao.get(id);
    }

    public void set(Integer id, UserCacheObject object) {
        userCacheDao.set(id, object);
    }

}
