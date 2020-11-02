package cn.iocoder.springboot.lab15.springdatasolr.repository;

import cn.iocoder.springboot.lab15.springdatasolr.dataobject.SolrProductDO;
import org.springframework.data.solr.repository.SolrCrudRepository;

public interface ProductRepository02 extends SolrCrudRepository<SolrProductDO, Integer> {

    SolrProductDO findByName(String name);

//    Page<SolrProductDO> findByNameLike(String name, Pageable pageable);

}
