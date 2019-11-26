package cn.iocoder.springboot.lab27.springwebflux.dao;

import cn.iocoder.springboot.lab27.springwebflux.dataobject.UserDO;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveElasticsearchRepository<UserDO, Integer> {

    Mono<UserDO> findByUsername(String username);

}

//public interface UserRepository extends ElasticsearchRepository<UserDO, Integer> {
//
////    Mono<UserDO> findByUsername(String username);
//
//}
