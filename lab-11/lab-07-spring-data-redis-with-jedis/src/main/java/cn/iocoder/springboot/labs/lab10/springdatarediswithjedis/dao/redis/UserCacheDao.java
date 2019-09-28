package cn.iocoder.springboot.labs.lab10.springdatarediswithjedis.dao.redis;

import cn.iocoder.springboot.labs.lab10.springdatarediswithjedis.cacheobject.UserCacheObject;
import cn.iocoder.springboot.labs.lab10.springdatarediswithjedis.util.JSONUtil;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class UserCacheDao {

    private static final String KEY_PATTERN = "user:%d"; // user:用户编号

//    @Autowired
//    private StringRedisTemplate template;
    @Resource(name = "redisTemplate")
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private ValueOperations<String, String> operations;

    private static String buildKey(Integer id) {
        return String.format(KEY_PATTERN, id);
    }

    public UserCacheObject get(Integer id) {
        String key = buildKey(id);
//        String value = template.opsForValue().get(key);
        String value = operations.get(key);
        return JSONUtil.parseObject(value, UserCacheObject.class);
    }

    public void set(Integer id, UserCacheObject object) {
        String key = buildKey(id);
        String value = JSONUtil.toJSONString(object);
//        template.opsForValue().set(key, value);
        operations.set(key, value);
    }

}
