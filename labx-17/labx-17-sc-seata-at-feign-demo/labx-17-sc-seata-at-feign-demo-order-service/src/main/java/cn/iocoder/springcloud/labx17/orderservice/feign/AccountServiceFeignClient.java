package cn.iocoder.springcloud.labx17.orderservice.feign;

import cn.iocoder.springcloud.labx17.orderservice.feign.dto.AccountReduceBalanceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "account-service")
public interface AccountServiceFeignClient {

    @PostMapping("/account/reduce-balance")
    void reduceBalance(@RequestBody AccountReduceBalanceDTO accountReduceBalanceDTO);

}
