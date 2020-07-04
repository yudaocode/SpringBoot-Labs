package cn.iocoder.springcloud.labx17.accountservice.controller;

import cn.iocoder.springcloud.labx17.accountservice.dto.AccountReduceBalanceDTO;
import cn.iocoder.springcloud.labx17.accountservice.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    private Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @PostMapping("/reduce-balance")
    public void reduceBalance(@RequestBody AccountReduceBalanceDTO accountReduceBalanceDTO) throws Exception {
        logger.info("[reduceBalance] 收到减少余额请求, 用户:{}, 金额:{}", accountReduceBalanceDTO.getUserId(),
                accountReduceBalanceDTO.getPrice());
        accountService.reduceBalance(accountReduceBalanceDTO.getUserId(), accountReduceBalanceDTO.getPrice());
    }

}
