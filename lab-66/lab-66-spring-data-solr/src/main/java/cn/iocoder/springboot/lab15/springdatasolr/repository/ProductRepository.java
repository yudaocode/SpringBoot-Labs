package cn.iocoder.springboot.lab15.springdatasolr.repository;

import cn.iocoder.springboot.lab15.springdatasolr.dataobject.SolrProductDO;
import org.springframework.data.solr.repository.SolrCrudRepository;

public interface ProductRepository extends SolrCrudRepository<SolrProductDO, Integer> {

}
