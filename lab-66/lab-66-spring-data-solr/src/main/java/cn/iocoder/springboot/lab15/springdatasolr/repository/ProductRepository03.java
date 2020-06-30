package cn.iocoder.springboot.lab15.springdatasolr.repository;

import cn.iocoder.springboot.lab15.springdatasolr.dataobject.SolrProductDO;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.List;

public interface ProductRepository03 extends SolrCrudRepository<SolrProductDO, Integer> {

    /**
     * 根据 name 匹配商品名或者商品分类，获得符合的商品列表
     */
    @Query("name:?0 OR category_name:?0")
    List<SolrProductDO> findByCustomQuery(String name);

}
