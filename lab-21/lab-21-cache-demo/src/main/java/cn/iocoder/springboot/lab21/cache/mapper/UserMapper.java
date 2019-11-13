package cn.iocoder.springboot.lab21.cache.mapper;

import cn.iocoder.springboot.lab21.cache.dataobject.UserDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<UserDO> {

    @Cacheable(value = "user")
    UserDO selectById(Integer id);

}
