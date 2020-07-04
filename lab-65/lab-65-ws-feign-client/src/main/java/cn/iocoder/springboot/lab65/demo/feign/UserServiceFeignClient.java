package cn.iocoder.springboot.lab65.demo.feign;

import cn.iocoder.springboot.lab65.demo.wsdl.UserCreateRequest;
import cn.iocoder.springboot.lab65.demo.wsdl.UserCreateResponse;
import cn.iocoder.springboot.lab65.demo.wsdl.UserGetRequest;
import cn.iocoder.springboot.lab65.demo.wsdl.UserGetResponse;
import feign.Headers;
import feign.RequestLine;

/**
 * 用户服务 Feign Client
 */
public interface UserServiceFeignClient {

    // 获得用户详情
    @RequestLine("POST /")
    @Headers("Content-Type: text/xml")
    UserGetResponse getUser(UserGetRequest request);

    // 创建用户
    @RequestLine("POST /")
    @Headers("Content-Type: text/xml")
    UserCreateResponse createUser(UserCreateRequest request);

}
