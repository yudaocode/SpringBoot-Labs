package cn.iocoder.springboot.lab16.springdatamongodb.dataobject;

import cn.iocoder.springboot.lab16.springdatamongodb.mongo.IncIdEntity;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 商品 DO
 */
@Document(collection = "Product")
public class ProductDO extends IncIdEntity<Integer> {

    /**
     * 商品名
     */
    private String name;

    public String getName() {
        return name;
    }

    public ProductDO setName(String name) {
        this.name = name;
        return this;
    }

}
