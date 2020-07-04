/**
 * 用于展示纯 Feign 的示例
 */
package cn.iocoder.springcloud.labx03.feigndemo.consumer;

import feign.Feign;
import feign.Param;
import feign.RequestLine;

// 商品 API
interface ProductAPI {

    // 获得商品详情
    @RequestLine("POST /products/{id}")
    String get(@Param("id") Integer id);

}

public class FeignDemo {

    public static void main(String[] args) {
        // 创建 ProductAPI 对象
        ProductAPI productAPI = Feign.builder().target(ProductAPI.class,
                "http://www.iocoder.cn"); // 目标地址

        // 调用获得商品
        String product = productAPI.get(1);
        System.out.println(product);
    }

}
