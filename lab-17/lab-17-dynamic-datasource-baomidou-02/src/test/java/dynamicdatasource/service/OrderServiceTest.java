package dynamicdatasource.service;

import cn.iocoder.springboot.lab17.dynamicdatasource.Application;
import cn.iocoder.springboot.lab17.dynamicdatasource.service.OrderService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

}
