package cn.iocoder.springboot.labs.lab10.springdatarediswithjedis;

import cn.iocoder.springboot.labs.lab10.springdatarediswithjedis.config.TestRedisConfiguration;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestRedisConfiguration.class)
public class Test01 {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

//    @Autowired
//    private RedisServer server;

    @Test
    public void test01() {
        // 写入
        stringRedisTemplate.opsForValue().set("yunai", "shuai");
        // 读取
        String value = stringRedisTemplate.opsForValue().get("yunai");
        Assert.assertEquals("值不匹配", "shuai", value);

        // 测试重启后读取
        redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.flushDb();
                return "";
            }
        });
    }

}
