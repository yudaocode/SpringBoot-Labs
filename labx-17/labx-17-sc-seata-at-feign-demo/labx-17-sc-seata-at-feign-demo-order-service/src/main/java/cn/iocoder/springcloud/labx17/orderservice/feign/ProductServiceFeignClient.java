package cn.iocoder.springcloud.labx17.orderservice.feign;

import cn.iocoder.springcloud.labx17.orderservice.feign.dto.ProductReduceStockDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * `product-service` 服务的 Feign 客户端
 */
@FeignClient(name = "product-service")
public interface ProductServiceFeignClient {

    @PostMapping("/product/reduce-stock")
    void reduceStock(@RequestBody ProductReduceStockDTO productReduceStockDTO);

}
