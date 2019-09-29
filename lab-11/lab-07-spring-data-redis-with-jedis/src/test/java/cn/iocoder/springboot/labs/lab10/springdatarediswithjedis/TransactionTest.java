package cn.iocoder.springboot.labs.lab10.springdatarediswithjedis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
//@EnableTransactionManagement
public class TransactionTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    @Transactional
    public void test01() {
        // 这里是偷懒，没在 RedisConfiguration 配置类中，设置 stringRedisTemplate 开启事务。
        stringRedisTemplate.setEnableTransactionSupport(true);

        // 执行想要的操作
        stringRedisTemplate.opsForValue().set("yunai:1", "shuai");
        stringRedisTemplate.opsForValue().set("yudaoyuanma:1", "dai");

//        stringRedisTemplate.execute(new SessionCallback<String>() {
//
//            @Override
//            public <K, V> String execute(RedisOperations<K, V> operations) throws DataAccessException {
//                return null;
//            }
//
////            @Override
////            public List<String> execute(RedisOperations<String, String> operations) throws DataAccessException {
////                for (int i = 0; i < 100; i++) {
////                    operations.opsForValue(String.format("yunai:%d", i), "shuai");
////                }
////                return null;
////            }
//
//        });
    }

    @Test
    public void test02() {
        stringRedisTemplate.setEnableTransactionSupport(true);


        stringRedisTemplate.opsForValue().get("user:1");
        stringRedisTemplate.opsForValue().get("user:2");
    }

}
