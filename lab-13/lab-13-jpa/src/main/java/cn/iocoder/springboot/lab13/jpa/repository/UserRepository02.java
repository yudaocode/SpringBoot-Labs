package cn.iocoder.springboot.lab13.jpa.repository;

import cn.iocoder.springboot.lab13.jpa.dataobject.UserDO;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository02 extends PagingAndSortingRepository<UserDO, Integer> {

}
