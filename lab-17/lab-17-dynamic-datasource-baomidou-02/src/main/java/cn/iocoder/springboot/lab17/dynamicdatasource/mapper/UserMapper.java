package cn.iocoder.springboot.lab17.dynamicdatasource.mapper;

import cn.iocoder.springboot.lab17.dynamicdatasource.constant.DBConstants;
import cn.iocoder.springboot.lab17.dynamicdatasource.dataobject.UserDO;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@DS(DBConstants.DATASOURCE_USERS)
public interface UserMapper {

    UserDO selectById(@Param("id") Integer id);

}
