package cn.iocoder.springboot.lab29.asynctask.service;

import cn.iocoder.springboot.lab29.asynctask.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class DemoServiceTest {

    @Autowired
    private DemoService demoService;

    @Test
    public void testExecute() throws InterruptedException {
        demoService.execute01();
        demoService.execute02();

        // sleep 1 秒，保证异步调用的执行
        Thread.sleep(1000);
    }

}
