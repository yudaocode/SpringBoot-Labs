package cn.iocoder.springboot.lab12.mybatis.mapper;

import cn.iocoder.springboot.lab12.mybatis.Application;
import cn.iocoder.springboot.lab12.mybatis.dataobject.UserDO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsert() {
        UserDO user = new UserDO().setUsername(UUID.randomUUID().toString())
                .setPassword("nicai").setCreateTime(new Date());
        userMapper.insert(user);
        System.out.println(user.getId());
    }

    @Test
    public void testUpdateById() {
        UserDO updateUser = new UserDO().setId(1)
                .setPassword("wobucai");
        userMapper.updateByPrimaryKey(updateUser);
    }

    @Test
    public void testDeleteById() {
        userMapper.deleteByPrimaryKey(2);
    }

    @Test
    public void testSelectById() {
        userMapper.selectByPrimaryKey(1);
    }

    @Test
    public void testSelectByUsername() {
        userMapper.selectByUsername("yunai");
    }

    @Test
    public void testSelectByIds() {
        List<UserDO> users = userMapper.selectByIds(Arrays.asList(1, 3));
        System.out.println("users：" + users.size());
    }

    @Test // 更多使用，可以参考 https://github.com/pagehelper/Mybatis-PageHelper/blob/master/wikis/zh/HowToUse.md
    public void testSelectPageByCreateTime() {
        // 设置分页
        PageHelper.startPage(1, 10);
        Date createTime = new Date(2018 - 1990, Calendar.FEBRUARY, 24); // 临时 Demo ，实际不建议这么写
        // 执行列表查询
        // PageHelper 会自动发起分页的数量查询，设置到 PageHelper 中
        List<UserDO> users = userMapper.selectListByCreateTime(createTime); // 实际返回的是 com.github.pagehelper.Page 代理对象
        // 转换成 PageInfo 对象，并输出分页
        PageInfo<UserDO> page = new PageInfo<>(users);
        System.out.println(page.getTotal());
    }

}
