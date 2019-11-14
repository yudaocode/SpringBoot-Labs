package cn.iocoder.springboot.lab21.cache.mapper;

import cn.iocoder.springboot.lab21.cache.dataobject.UserDO;
import org.springframework.stereotype.Repository;

@Repository
public class UserCacheDao {

    public UserDO get(Integer id) {
        return new UserDO();
    }

    public void put(UserDO user) {
    }

}
