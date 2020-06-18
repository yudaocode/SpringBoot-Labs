package cn.iocoder.springboot.lab15.springdatasolr.repository;

import cn.iocoder.springboot.lab15.springdatasolr.Application;
import cn.iocoder.springboot.lab15.springdatasolr.dataobject.SolrProductDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ProductRepository03Test {

    @Autowired
    private ProductRepository03 productRepository;

    @Test
    public void testFindByCustomQuery() {
        List<SolrProductDO> products = productRepository.findByCustomQuery("技术");
        System.out.println(products.size());
    }

}
