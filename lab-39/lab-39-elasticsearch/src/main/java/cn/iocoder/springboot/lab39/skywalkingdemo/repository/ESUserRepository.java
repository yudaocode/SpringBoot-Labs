package cn.iocoder.springboot.lab39.skywalkingdemo.repository;

import cn.iocoder.springboot.lab39.skywalkingdemo.dataobject.ESUserDO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ESUserRepository extends ElasticsearchRepository<ESUserDO, Integer> {

}
