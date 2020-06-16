package cn.iocoder.springboot.lab65.userservice.service;

import cn.iocoder.springboot.lab65.userservice.config.WebServicesConfig;
import cn.iocoder.springboot.lab65.userservice.request.UserGetRequest;
import cn.iocoder.springboot.lab65.userservice.response.UserGetResponse;

import javax.jws.WebService;

@WebService(targetNamespace = WebServicesConfig.NAMESPACE_URI
//        ,
//        name = "userPortName"
)
public interface UserService {

    UserGetResponse get(UserGetRequest request);

}
