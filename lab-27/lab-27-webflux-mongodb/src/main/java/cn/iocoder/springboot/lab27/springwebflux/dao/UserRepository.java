package cn.iocoder.springboot.lab27.springwebflux.dao;

import cn.iocoder.springboot.lab27.springwebflux.dataobject.UserDO;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<UserDO, Integer> {

    Mono<UserDO> findByUsername(String username);

}
