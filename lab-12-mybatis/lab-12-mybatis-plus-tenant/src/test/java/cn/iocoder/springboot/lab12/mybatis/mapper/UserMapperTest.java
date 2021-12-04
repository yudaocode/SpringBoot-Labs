package cn.iocoder.springboot.lab12.mybatis.mapper;

import cn.iocoder.springboot.lab12.mybatis.Application;
import cn.iocoder.springboot.lab12.mybatis.dataobject.UserDO;
import cn.iocoder.springboot.lab12.mybatis.dataobject.UserProfileDO;
import cn.iocoder.springboot.lab12.mybatis.vo.UserDetailVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
    @Autowired
    private UserProfileMapper userProfileMapper;

    @Test
    public void initTestData() {
        // 清理数据
        userMapper.delete(new QueryWrapper<>());
        userProfileMapper.delete(new QueryWrapper<>());
        // 插入一个用户
        UserDO userDO = new UserDO().setUsername(UUID.randomUUID().toString())
                .setPassword("nicai").setCreateTime(new Date())
                .setDeleted(0); // 一般情况下，是否删除，可以全局枚举下。
        userMapper.insert(userDO);
        // 插入该用户的拓展信息
        UserProfileDO userProfileDO = new UserProfileDO();
        userProfileDO.setUserId(userDO.getId());
        userProfileDO.setGender(1);
        userProfileDO.setTenantId(10); // TODO 全局写死
        userProfileDO.setDeleted(0); // 一般情况下，是否删除，可以全局枚举下。
        userProfileMapper.insert(userProfileDO);
    }

    @Test
    public void testInsert() {
        UserDO user = new UserDO().setUsername(UUID.randomUUID().toString())
                .setPassword("nicai").setCreateTime(new Date())
                .setDeleted(0); // 一般情况下，是否删除，可以全局枚举下。
        userMapper.insert(user);
    }

    @Test
    public void testUpdateById() {
        UserDO updateUser = new UserDO().setId(1)
                .setPassword("wobucai");
        userMapper.updateById(updateUser);
    }

    @Test
    public void testDeleteById() {
        userMapper.deleteById(2);
    }

    @Test
    public void testSelectById() {
        userMapper.selectById(1);
    }

    @Test
    public void testSelectByUsername() {
        UserDO userDO = userMapper.selectByUsername("yunai");
        System.out.println(userDO);
    }

    @Test
    public void testSelectByIds() {
        List<UserDO> users = userMapper.selectByIds(Arrays.asList(1, 3));
        System.out.println("users：" + users.size());
    }

    @Test
    public void testSelectPageByCreateTime() {
        IPage<UserDO> page = new Page<>(1, 10);
        Date createTime = new Date(2018 - 1990, Calendar.FEBRUARY, 24); // 临时 Demo ，实际不建议这么写
        page = userMapper.selectPageByCreateTime(page, createTime);
        System.out.println("users：" + page.getRecords().size());
    }

    @Test
    public void testSelectListA() {
        List<UserDetailVO> list = userMapper.selectListA();
        System.out.println(list);
    }

    @Test
    public void testSelectListB() {
        List<UserDetailVO> list = userMapper.selectListB();
        System.out.println(list);
    }

}
