package cn.iocoder.springboot.lab21.cache;

import cn.iocoder.springboot.lab21.cache.dataobject.UserDO;
import cn.iocoder.springboot.lab21.cache.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserMapperTest {

    private static final String CACHE_NAME_USER = "users";

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CacheManager cacheManager;

    @Test
    public void testCacheManager() {
        System.out.println(cacheManager);
    }

    @Test
    public void testSelectById() {
        // 这里，胖友事先插入一条 id = 1 的记录。
        Integer id = 1;

        // 查询 id = 1 的记录
        UserDO user = userMapper.selectById(id);
        System.out.println("user：" + user);
        // 判断缓存中，是不是存在
        Assert.assertNotNull("缓存为空", cacheManager.getCache(CACHE_NAME_USER).get(user.getId(), UserDO.class));

        // 查询 id = 1 的记录
        user = userMapper.selectById(id);
        System.out.println("user：" + user);
    }

    @Test
    public void testInsert() {
        // 插入记录
        UserDO user = new UserDO();
        user.setUsername(UUID.randomUUID().toString()); // 随机账号，因为唯一索引
        user.setPassword("nicai");
        user.setCreateTime(new Date());
        user.setDeleted(0);
        userMapper.insert0(user);

        // 判断缓存中，是不是存在
        Assert.assertNotNull("缓存为空", cacheManager.getCache(CACHE_NAME_USER).get(user.getId(), UserDO.class));
    }

    @Test
    public void testDeleteById() {
        // 插入记录，为了让缓存里有记录
        UserDO user = new UserDO();
        user.setUsername(UUID.randomUUID().toString()); // 随机账号，因为唯一索引
        user.setPassword("nicai");
        user.setCreateTime(new Date());
        user.setDeleted(0);
        userMapper.insert0(user);
        // 判断缓存中，是不是存在
        Assert.assertNotNull("缓存为空", cacheManager.getCache(CACHE_NAME_USER).get(user.getId(), UserDO.class));

        // 删除记录，为了让缓存被删除
        userMapper.deleteById(user.getId());
        // 判断缓存中，是不是存在
        Assert.assertNull("缓存不为空", cacheManager.getCache(CACHE_NAME_USER).get(user.getId(), UserDO.class));
    }

}
