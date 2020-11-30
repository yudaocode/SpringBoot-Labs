package cn.iocoder.springboot.lab16.springdatamongodb.repository;

import cn.iocoder.springboot.lab16.springdatamongodb.dataobject.ProductDO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<ProductDO, Integer> {
}
