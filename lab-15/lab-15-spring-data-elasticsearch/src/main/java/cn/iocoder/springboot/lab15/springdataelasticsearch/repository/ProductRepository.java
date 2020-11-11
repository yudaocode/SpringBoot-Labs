package cn.iocoder.springboot.lab15.springdataelasticsearch.repository;

import cn.iocoder.springboot.lab15.springdataelasticsearch.dataobject.ESProductDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ProductRepository extends ElasticsearchRepository<ESProductDO, Integer> {


    List<ESProductDO> findFirstByCategoryName(String categoryName);

    /**
     * 根据分类名进行查询
     * 会按分词器分词分词之后进行查询
     * @param categoryName
     * @return
     */
    List<ESProductDO> findAllByCategoryName(String categoryName);

    List<ESProductDO> findByDescriptionOrSellPoint(Sort sort, String description, String sellPoint);

    Page<ESProductDO> findByDescriptionOrSellPoint(Pageable pageable, String description, String sellPoint);
}
