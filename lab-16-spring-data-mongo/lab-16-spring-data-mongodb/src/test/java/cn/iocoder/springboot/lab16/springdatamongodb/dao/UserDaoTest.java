package cn.iocoder.springboot.lab16.springdatamongodb.dao;

import cn.iocoder.springboot.lab16.springdatamongodb.Application;
import cn.iocoder.springboot.lab16.springdatamongodb.dataobject.UserDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test // 插入一条记录
    public void testInsert() {
        // 创建 UserDO 对象
        UserDO user = new UserDO();
        user.setId(1); // 这里先临时写死一个 ID 编号，后面演示自增 ID 的时候，在修改这块
        user.setUsername("yudaoyuanma");
        user.setPassword("buzhidao");
        user.setCreateTime(new Date());
        // 创建 Profile 对象
        UserDO.Profile profile = new UserDO.Profile();
        profile.setNickname("芋道源码");
        profile.setGender(1);
        user.setProfile(profile);
        // 存储到 DB
        userDao.insert(user);
    }

    // 这里要注意，如果使用 save 方法来更新的话，必须是全量字段，否则其它字段会被覆盖。
    // 所以，这里仅仅是作为一个示例。
    @Test // 更新一条记录
    public void testUpdate() {
        // 创建 UserDO 对象
        UserDO updateUser = new UserDO();
        updateUser.setId(1);
        updateUser.setUsername("nicai");

        // 执行更新
        userDao.updateById(updateUser);
    }

    @Test // 根据 ID 编号，删除一条记录
    public void testDelete() {
        userDao.deleteById(1);
    }

    @Test // 根据 ID 编号，查询一条记录
    public void testSelectById() {
        UserDO userDO = userDao.findById(1);
        System.out.println(userDO);
    }

    @Test // 根据 ID 编号数组，查询多条记录
    public void testSelectByIds() {
        List<UserDO> users = userDao.findAllById(Arrays.asList(1, 4));
        users.forEach(System.out::println);
    }

}
