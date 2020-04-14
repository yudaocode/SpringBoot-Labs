package cn.iocoder.springcloud.labx13.springmvcdemo.repository;

import cn.iocoder.springcloud.labx13.springmvcdemo.dataobject.ESUserDO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ESUserRepository extends ElasticsearchRepository<ESUserDO, Integer> {

}
