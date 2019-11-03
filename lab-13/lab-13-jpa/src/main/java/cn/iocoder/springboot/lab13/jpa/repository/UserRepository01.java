package cn.iocoder.springboot.lab13.jpa.repository;

import cn.iocoder.springboot.lab13.jpa.dataobject.UserDO;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository01 extends CrudRepository<UserDO, Integer> {

}
