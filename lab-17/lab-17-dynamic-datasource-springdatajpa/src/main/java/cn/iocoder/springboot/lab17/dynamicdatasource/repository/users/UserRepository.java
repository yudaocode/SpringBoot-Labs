package cn.iocoder.springboot.lab17.dynamicdatasource.repository.users;

import cn.iocoder.springboot.lab17.dynamicdatasource.dataobject.UserDO;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserDO, Integer> {

}
