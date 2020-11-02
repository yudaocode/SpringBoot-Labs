package cn.iocoder.springboot.lab13.jpa.repository;

import cn.iocoder.springboot.lab13.jpa.dataobject.UserDO;
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
@SpringBootTest
public class UserRepository02Test {

    @Autowired
    private UserRepository02 userRepository;

    @Test // 排序
    public void testFindAll() {
        // 创建排序条件
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        // 执行排序操作
        Iterable<UserDO> iterable = userRepository.findAll(sort);
        // 打印
        iterable.forEach(System.out::println);
    }

    @Test // 分页
    public void testFindPage() {
        // 创建排序条件
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        // 创建分页条件
        Pageable pageable = PageRequest.of(1, 10, sort);
        // 执行分页操作
        Page<UserDO> page = userRepository.findAll(pageable);
        // 打印
        System.out.println(page.getTotalElements());
        System.out.println(page.getTotalPages());
    }

}
