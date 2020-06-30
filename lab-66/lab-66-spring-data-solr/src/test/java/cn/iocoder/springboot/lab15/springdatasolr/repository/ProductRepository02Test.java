package cn.iocoder.springboot.lab15.springdatasolr.repository;

import cn.iocoder.springboot.lab15.springdatasolr.Application;
import cn.iocoder.springboot.lab15.springdatasolr.dataobject.SolrProductDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ProductRepository02Test {

    @Autowired
    private ProductRepository02 productRepository;

    @Test // 根据名字获得一条记录
    public void testFindByName() {
        SolrProductDO product = productRepository.findByName("芋道源码");
        System.out.println(product);
    }

//    @Test // 使用 name 模糊查询，分页返回结果
//    public void testFindByNameLike() {
//        // 根据情况，是否要制造测试数据
//        if (false) {
//            testInsert();
//        }
//
//        // 创建排序条件
//        Sort sort = Sort.by(Sort.Direction.DESC, "id");  // ID 倒序
//        // 创建分页条件。
//        Pageable pageable = PageRequest.of(0, 10, sort);
//        // 执行分页操作
//        Page<SolrProductDO> page = productRepository.findByNameLike("芋道", pageable);
//        // 打印
//        System.out.println(page.getTotalElements());
//        System.out.println(page.getTotalPages());
//    }
//
//    /**
//     * 为了给分页制造一点数据
//     */
//    private void testInsert() {
//        for (int i = 1; i <= 100; i++) {
//            SolrProductDO product = new SolrProductDO();
//            product.setId(i); // 一般 ES 的 ID 编号，使用 DB 数据对应的编号。这里，先写死
//            product.setName("芋道源码：" + i);
//            product.setDescription("我只是一个描述");
//            product.setCid(1);
//            product.setCategoryName("技术");
//            productRepository.save(product);
//        }
//    }

}
