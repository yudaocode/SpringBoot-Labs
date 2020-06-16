package cn.iocoder.springboot.lab65.userservice.service;

import cn.iocoder.springboot.lab65.userservice.config.CXFConfig;
import cn.iocoder.springboot.lab65.userservice.request.UserCreateRequest;
import cn.iocoder.springboot.lab65.userservice.request.UserGetRequest;
import cn.iocoder.springboot.lab65.userservice.response.UserCreateResponse;
import cn.iocoder.springboot.lab65.userservice.response.UserGetResponse;

import javax.jws.WebService;

@WebService(targetNamespace = CXFConfig.NAMESPACE_URI)
public interface UserService {

    UserGetResponse get(UserGetRequest request);

    UserCreateResponse create(UserCreateRequest request);

}
