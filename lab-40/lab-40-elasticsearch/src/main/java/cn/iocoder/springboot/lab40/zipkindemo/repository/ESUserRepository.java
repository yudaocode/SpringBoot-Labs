package cn.iocoder.springboot.lab40.zipkindemo.repository;

import cn.iocoder.springboot.lab40.zipkindemo.dataobject.ESUserDO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ESUserRepository extends ElasticsearchRepository<ESUserDO, Integer> {

}
