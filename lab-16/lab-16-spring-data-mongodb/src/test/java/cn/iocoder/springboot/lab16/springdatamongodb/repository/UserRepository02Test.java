package cn.iocoder.springboot.lab16.springdatamongodb.repository;

import cn.iocoder.springboot.lab16.springdatamongodb.Application;
import cn.iocoder.springboot.lab16.springdatamongodb.dataobject.UserDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserRepository02Test {

    @Autowired
    private UserRepository02 userRepository;

    @Test // 根据名字获得一条记录
    public void testFindByName() {
        UserDO user = userRepository.findByUsername("yutou");
        System.out.println(user);
    }

    @Test // 使用 username 模糊查询，分页返回结果
    public void testFindByNameLike() {
        // 创建排序条件
        Sort sort = new Sort(Sort.Direction.DESC, "id"); // ID 倒序
        // 创建分页条件。
        Pageable pageable = PageRequest.of(0, 10, sort);
        // 执行分页操作
        Page<UserDO> page = userRepository.findByUsernameLike("yu", pageable);
        // 打印
        System.out.println(page.getTotalElements());
        System.out.println(page.getTotalPages());
    }

}
