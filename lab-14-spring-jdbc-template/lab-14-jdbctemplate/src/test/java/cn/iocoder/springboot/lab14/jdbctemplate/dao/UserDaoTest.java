package cn.iocoder.springboot.lab14.jdbctemplate.dao;

import cn.iocoder.springboot.lab14.jdbctemplate.Application;
import cn.iocoder.springboot.lab14.jdbctemplate.dataobject.UserDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void testInsert() {
        UserDO user = new UserDO().setUsername(UUID.randomUUID().toString())
                .setPassword("nicai").setCreateTime(new Date());
        userDao.insert(user);
        System.out.println(user);
    }

    @Test
    public void testInsert0() {
        UserDO user = new UserDO().setUsername(UUID.randomUUID().toString())
                .setPassword("nicai").setCreateTime(new Date());
        userDao.insert0(user);
        System.out.println(user);
    }

    @Test
    public void testUpdateById() {
        UserDO updateUser = new UserDO().setId(1)
                .setPassword("wobucai");
        userDao.updateById(updateUser);
    }

    @Test
    public void testDeleteById() {
        userDao.deleteById(2);
    }

    @Test
    public void testSelectById() {
        UserDO user = userDao.selectById(1);
        System.out.println(user);
    }

    @Test
    public void testSelectByUsername() {
        UserDO user = userDao.selectByUsername("yunai");
        System.out.println(user);
    }

    @Test
    public void testSelectByIds() {
        List<UserDO> users = userDao.selectByIds(Arrays.asList(1, 5));
        System.out.println("users：" + users.size());
    }

}
