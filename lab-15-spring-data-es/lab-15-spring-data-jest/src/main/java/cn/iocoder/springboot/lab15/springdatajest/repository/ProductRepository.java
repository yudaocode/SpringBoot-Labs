package cn.iocoder.springboot.lab15.springdatajest.repository;

import cn.iocoder.springboot.lab15.springdatajest.dataobject.ESProductDO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductRepository extends ElasticsearchRepository<ESProductDO, Integer> {

}
