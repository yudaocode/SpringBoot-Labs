package cn.iocoder.springboot.lab15.springdatajest.repository;

import cn.iocoder.springboot.lab15.springdatajest.Application;
import cn.iocoder.springboot.lab15.springdatajest.dataobject.ESProductDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ProductRepository03Test {

    @Autowired
    private ProductRepository03 productRepository;

    @Test
    public void testSearch() {
        // 查找分类为 1 + 指定关键字，并且按照 id 升序
        Page<ESProductDO> page = productRepository.search(1, "技术",
                PageRequest.of(0, 5, Sort.Direction.ASC, "id"));
        System.out.println(page.getTotalPages());

        // 查找分类为 1 ，并且按照 id 升序
        page = productRepository.search(1, null,
                PageRequest.of(0, 5, Sort.Direction.ASC, "id"));
        System.out.println(page.getTotalPages());
    }

}
