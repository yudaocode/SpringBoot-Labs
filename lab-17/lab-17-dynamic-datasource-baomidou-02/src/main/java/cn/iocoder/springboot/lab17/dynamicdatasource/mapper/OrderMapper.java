package cn.iocoder.springboot.lab17.dynamicdatasource.mapper;

import cn.iocoder.springboot.lab17.dynamicdatasource.constant.DBConstants;
import cn.iocoder.springboot.lab17.dynamicdatasource.dataobject.OrderDO;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper {

    @DS(DBConstants.DATASOURCE_SLAVE)
    OrderDO selectById(@Param("id") Integer id);

    @DS(DBConstants.DATASOURCE_MASTER)
    int insert(OrderDO entity);

}
