package cn.iocoder.springboot.lab16.springdatamongodb.dao;

import cn.iocoder.springboot.lab16.springdatamongodb.dataobject.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;

@Repository
public class UserDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void insert(UserDO entity) {
        mongoTemplate.insert(entity);
    }

    public void updateById(UserDO entity) {
        // 生成 Update 条件
        final Update update = new Update();
        // 反射遍历 entity 对象，将非空字段设置到 Update 中
        ReflectionUtils.doWithFields(entity.getClass(), new ReflectionUtils.FieldCallback() {

            @Override
            public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                // 排除指定条件
                if ("id".equals(field.getName()) // 排除 id 字段，因为作为查询主键
                        || field.getAnnotation(Transient.class) != null // 排除 @Transient 注解的字段，因为非存储字段
                        || Modifier.isStatic(field.getModifiers())) { // 排除静态字段
                    return;
                }
                // 设置字段可反射
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                // 排除字段为空的情况
                if (field.get(entity) == null) {
                    return;
                }
                // 设置更新条件
                update.set(field.getName(), field.get(entity));
            }

        });
        // 防御，避免有业务传递空的 Update 对象
        if (update.getUpdateObject().isEmpty()) {
            return;
        }
        // 执行更新
        mongoTemplate.updateFirst(new Query(Criteria.where("_id").is(entity.getId())), update, UserDO.class);
    }

    public void deleteById(Integer id) {
        mongoTemplate.remove(new Query(Criteria.where("_id").is(id)), UserDO.class);
    }

    public UserDO findById(Integer id) {
        return mongoTemplate.findOne(new Query(Criteria.where("_id").is(id)), UserDO.class);
    }

    public UserDO findByUsername(String username) {
        return mongoTemplate.findOne(new Query(Criteria.where("username").is(username)), UserDO.class);
    }

    public List<UserDO> findAllById(List<Integer> ids) {
        return mongoTemplate.find(new Query(Criteria.where("_id").in(ids)), UserDO.class);
    }

}
