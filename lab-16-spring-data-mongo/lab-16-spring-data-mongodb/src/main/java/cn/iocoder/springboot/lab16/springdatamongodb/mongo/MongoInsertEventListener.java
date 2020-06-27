package cn.iocoder.springboot.lab16.springdatamongodb.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class MongoInsertEventListener extends AbstractMongoEventListener<IncIdEntity> {

    /**
     * sequence - 集合名
     */
    private static final String SEQUENCE_COLLECTION_NAME = "sequence";
    /**
     * sequence - 自增值的字段名
     */
    private static final String SEQUENCE_FIELD_VALUE = "value";

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<IncIdEntity> event) {
        IncIdEntity entity = event.getSource();
        // 判断 id 为空
        if (entity.getId() == null) {
            // 获得下一个编号
            Number id = this.getNextId(entity);
            // 设置到实体中
            // noinspection unchecked
            entity.setId(id);
        }
    }

    /**
     * 获得实体的下一个主键 ID 编号
     *
     * @param entity 实体对象
     * @return ID 编号
     */
    private Number getNextId(IncIdEntity entity) {
        // 使用实体名的简单类名，作为 ID 编号
        String id = entity.getClass().getSimpleName();
        // 创建 Query 对象
        Query query = new Query(Criteria.where("_id").is(id));
        // 创建 Update 对象
        Update update = new Update();
        update.inc(SEQUENCE_FIELD_VALUE, 1); // 自增值
        // 创建 FindAndModifyOptions 对象
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.upsert(true); // 如果不存在时，则进行插入
        options.returnNew(true); // 返回新值
        // 执行操作
        @SuppressWarnings("unchecked")
        HashMap<String, Object> result = mongoTemplate.findAndModify(query, update, options,
                HashMap.class, SEQUENCE_COLLECTION_NAME);
        // 返回主键
        return (Number) result.get(SEQUENCE_FIELD_VALUE);
    }

}
