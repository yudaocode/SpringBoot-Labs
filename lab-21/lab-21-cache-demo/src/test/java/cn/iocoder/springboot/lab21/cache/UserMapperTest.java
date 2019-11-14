package cn.iocoder.springboot.lab21.cache;

import cn.iocoder.springboot.lab21.cache.dataobject.UserDO;
import cn.iocoder.springboot.lab21.cache.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectById() {
        UserDO user = userMapper.selectById(2);
        System.out.println("user：" + user);
        user = userMapper.selectById(2);
        System.out.println("user：" + user);
    }

    @Test
    public void testInsert () {
        // 插入记录
        UserDO user = new UserDO();
        user.setUsername(UUID.randomUUID().toString()); // 随机账号，因为唯一索引
        user.setPassword("nicai");
        user.setCreateTime(new Date());
        user.setDeleted(0);
        userMapper.insert0(user);

        // 查询数据
        user = userMapper.selectById(user.getId());
        System.out.println(user);
    }

}
