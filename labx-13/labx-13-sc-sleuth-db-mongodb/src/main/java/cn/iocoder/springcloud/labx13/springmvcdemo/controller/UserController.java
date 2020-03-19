package cn.iocoder.springcloud.labx13.springmvcdemo.controller;

import cn.iocoder.springcloud.labx13.springmvcdemo.dataobject.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/get")
    public String get(@RequestParam("id") Integer id) {
        this.findById(1);
        return "success";
    }

    public UserDO findById(Integer id) {
        return mongoTemplate.findOne(new Query(Criteria.where("_id").is(id)), UserDO.class);
    }

}
