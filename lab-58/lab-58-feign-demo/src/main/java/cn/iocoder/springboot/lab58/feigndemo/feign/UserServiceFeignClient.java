package cn.iocoder.springboot.lab58.feigndemo.feign;

import cn.iocoder.springboot.lab58.feigndemo.feign.request.UserAddRequest;
import cn.iocoder.springboot.lab58.feigndemo.feign.response.UserResponse;
import feign.*;

import java.util.List;
import java.util.Map;

/**
 * 基于 Contract.Default 默认契约
 */
public interface UserServiceFeignClient {

    // 获得用户详情
    @RequestLine("GET /user/get?id={id}")
    UserResponse get(@Param("id") Integer id);

    @RequestLine("GET /user/list?name={name}&gender={gender}")
    List<UserResponse> list(@Param("name") String name,
                            @Param("gender") Integer gender);

    @RequestLine("GET /user/list")
    List<UserResponse> list(@QueryMap Map<String, Object> queryMap);

    @RequestLine("POST /user/add")
    @Headers("Content-Type: application/json")
    Integer add(UserAddRequest request);

}
