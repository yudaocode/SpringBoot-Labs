package cn.iocoder.springboot.lab55.mapstructdemo.convert;

import cn.iocoder.springboot.lab55.mapstructdemo.bo.UserBO;
import cn.iocoder.springboot.lab55.mapstructdemo.dataobject.UserDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    UserBO convert(UserDO userDO);

}
