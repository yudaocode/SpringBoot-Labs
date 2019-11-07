package cn.iocoder.springboot.lab17.dynamicdatasource.mapper;

import cn.iocoder.springboot.lab17.dynamicdatasource.dataobject.UserDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    UserDO selectById(@Param("id") Integer id);

}
