package cn.iocoder.springboot.lab15.springdataelasticsearch.repository;

import cn.iocoder.springboot.lab15.springdataelasticsearch.dataobject.ESProductDO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductRepository extends ElasticsearchRepository<ESProductDO, Integer> {

}
