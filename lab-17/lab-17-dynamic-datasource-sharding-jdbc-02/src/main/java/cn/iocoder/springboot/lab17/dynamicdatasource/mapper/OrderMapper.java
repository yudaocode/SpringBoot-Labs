package cn.iocoder.springboot.lab17.dynamicdatasource.mapper;

import cn.iocoder.springboot.lab17.dynamicdatasource.dataobject.OrderDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper {

    OrderDO selectById(@Param("id") Integer id);

    int insert(OrderDO entity);

}
