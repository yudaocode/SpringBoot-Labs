package cn.iocoder.springboot.lab27.springwebflux.dao;

import cn.iocoder.springboot.lab27.springwebflux.dataobject.UserDO;
import org.springframework.data.r2dbc.repository.query.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<UserDO, Integer> {

    @Query("SELECT id, username, password, create_time FROM users WHERE username = :username")
    Mono<UserDO> findByUsername(String username);

}
