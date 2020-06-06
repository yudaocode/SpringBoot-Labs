package cn.iocoder.springboot.lab61.catdemo.controller;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/transaction")
    public String echo() {
        Transaction t = Cat.newTransaction("URL", "pageName");
        try {

//            yourBusiness();

            t.setStatus(Transaction.SUCCESS);
        } catch (Exception e) {
            t.setStatus(e);
        } finally {
            t.complete();
        }
        return "success";
    }



}
