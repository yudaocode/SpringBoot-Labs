package cn.iocoder.springboot.lab61.catdemo.controller;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.dianping.cat.message.Transaction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    /**
     * 监控模型 Transaction 的示例
     */
    @GetMapping("/transaction")
    public String transaction() {
        // 创建 Transaction 对象
        Transaction t = Cat.newTransaction("URL", "/demo/transaction");
        try {
            // ... yourBusiness(); 业务逻辑

            // 设置 Transaction 的状态为成功
            t.setStatus(Transaction.SUCCESS);
        } catch (Exception e) {
            // 设置 Transaction 的状态为异常
            t.setStatus(e);
        } finally {
            // 标记 Transaction 结束
            t.complete();
        }
        return "success";
    }

    /**
     * 监控模型 Event 的示例 01
     */
    @GetMapping("/event-01")
    public String event01() {
        // Cat.logEvent("URL.Server", "127.0.0.1");
        Cat.logEvent("URL.Server", "127.0.0.1", Event.SUCCESS, "data");
        return "success";
    }

    /**
     * 监控模型 Event 的示例 02
     */
    @GetMapping("/event-02")
    public String event02() {
        try {
            int result = 1 / 0;
        } catch (Throwable e) {
            Cat.logError(e);
            // Cat.logError("custom-message", e);
        }
        return "success";
    }

    /**
     * 监控模型 Event 的示例 03
     */
    @GetMapping("/event-03")
    public String event03() {
        try {
            int result = 1 / 0;
        } catch (Throwable e) {
            Cat.logErrorWithCategory("custom-category", e);
            // Cat.logErrorWithCategory("custom-category", "custom-message", e);
        }
        return "success";
    }

    /**
     * 监控模型 Metric 示例 01
     */
    @GetMapping("/metric-01")
    public String metric01() {
        Cat.logMetricForCount("visit.count", 1);
        return "success";
    }

    /**
     * 监控模型 Metric 示例 02
     */
    @GetMapping("/metric-02")
    public String metric02() {
        Cat.logMetricForDuration("visit.duration", 10L);
        return "success";
    }

}
