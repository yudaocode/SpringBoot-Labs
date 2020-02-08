package cn.iocoder.springboot.lab40.zipkin.controller;

import cn.iocoder.springboot.lab40.zipkin.dataobject.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/mongodb")
    public String mysql() {
        this.findById(1);
        return "mongodb";
    }

    public UserDO findById(Integer id) {
        return mongoTemplate.findOne(new Query(Criteria.where("_id").is(id)), UserDO.class);
    }

}
