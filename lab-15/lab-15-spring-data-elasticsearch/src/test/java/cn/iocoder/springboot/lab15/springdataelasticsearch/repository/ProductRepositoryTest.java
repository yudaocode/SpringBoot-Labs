package cn.iocoder.springboot.lab15.springdataelasticsearch.repository;

import cn.iocoder.springboot.lab15.springdataelasticsearch.Application;
import cn.iocoder.springboot.lab15.springdataelasticsearch.dataobject.ESProductDO;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.data.querydsl.QSort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test // 插入一条记录
    public void testInsert() {
        for (int i = 0; i < 10; i++) {
            ESProductDO product = new ESProductDO();
            product.setId(i+1); // 一般 ES 的 ID 编号，使用 DB 数据对应的编号。这里，先写死
            product.setName("芋道源码");
            if(i%2 == 1){
                product.setSellPoint("愿半生编码，如一个老友");
                product.setDescription("我只是一条描述");
            }else{
                product.setSellPoint("愿半生编码，如一条老友");
                product.setDescription("我只是一个描述");
            }
            product.setCid(2);
            product.setCategoryName("技术"+i);
            productRepository.save(product);
        }

    }

    // 这里要注意，如果使用 save 方法来更新的话，必须是全量字段，否则其它字段会被覆盖。
    // 所以，这里仅仅是作为一个示例。
    @Test // 更新一条记录
    public void testUpdate() {
        ESProductDO product = new ESProductDO();
        product.setId(1);
        product.setCid(2);
        product.setCategoryName("技术-Java");
        productRepository.save(product);
    }

    @Test // 根据 ID 编号，删除一条记录
    public void testDelete() {
//        productRepository.deleteById(1);
        productRepository.deleteAll();
    }

    @Test // 根据 ID 编号，查询一条记录
    public void testSelectById() {
        Optional<ESProductDO> userDO = productRepository.findById(1);
        System.out.println(userDO.isPresent());
        System.out.println(userDO.get());
    }

    @Test // 根据 ID 编号数组，查询多条记录
    public void testSelectByIds() {
        Iterable<ESProductDO> users = productRepository.findAllById(Arrays.asList(1, 4));
        users.forEach(System.out::println);
    }

    /**
     * 测试jpa方法
     */
    @Test
    public void test1(){
//        List<ESProductDO> list = productRepository.findAllByCategoryName("技术");
//        List<ESProductDO> f = productRepository.findFirstByCategoryName("技术");
        //字段排序
        Sort sort = Sort.by("id").descending();
//        List<ESProductDO> list = productRepository.findByDescriptionOrSellPoint(sort,"一个","一个");
//        list.forEach(System.out::println);

        //排序并分页
        PageRequest request = PageRequest.of(0,2,sort);
        Page<ESProductDO> page = productRepository.findByDescriptionOrSellPoint(request,"一个","一个");
        page.get().forEach(System.out::println);

    }



}
