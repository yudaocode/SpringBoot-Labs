package cn.iocoder.springboot.lab13.jpa.repository;

import cn.iocoder.springboot.lab13.jpa.dataobject.UserDO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository04 extends PagingAndSortingRepository<UserDO, Integer> {

    @Query("SELECT u FROM UserDO u WHERE u.username = ?1")
    UserDO findByUsername01(String username);

    @Query("SELECT u FROM UserDO u WHERE u.username = :username")
    UserDO findByUsername02(@Param("username") String username);

    @Query(value = "SELECT * FROM users u WHERE u.username = :username", nativeQuery = true)
    UserDO findByUsername03(@Param("username") String username);

    @Query("UPDATE UserDO  u SET u.username = :username WHERE u.id = :id")
    @Modifying
    int updateUsernameById(Integer id, String username);

}
