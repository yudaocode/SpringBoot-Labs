package cn.iocoder.springboot.lab65.userservice.service;

import cn.iocoder.springboot.lab65.userservice.config.WebServicesConfig;
import cn.iocoder.springboot.lab65.userservice.request.UserGetRequest;
import cn.iocoder.springboot.lab65.userservice.response.UserGetResponse;
import org.springframework.stereotype.Service;

import javax.jws.WebService;

@WebService(
        serviceName = "userService", // 服务名称
        targetNamespace = WebServicesConfig.NAMESPACE_URI, // WSDL 命名空间
        endpointInterface = "cn.iocoder.springboot.lab65.userservice.service.UserService" // Web Services 对应接口
//        name = "userPortType", // portType 名称，作为客户端生成代码时的接口名称 TODO
//        portName = "userPortName"  // port 名称 TODO
)
@Service
public class UserServiceImpl implements UserService {

    @Override
    public UserGetResponse get(UserGetRequest request) {
        UserGetResponse response = new UserGetResponse();
        response.setId(request.getId());
        response.setName("没有昵称：" + request.getId());
        response.setGender(request.getId() % 2 + 1);
        return response;
    }

//    @PayloadRoot(namespace = WebServicesConfig.NAMESPACE_URI, localPart = "UserCreateRequest")
//    @ResponsePayload
//    public UserCreateResponse create(@RequestPayload UserCreateRequest request) {
//        UserCreateResponse response = new UserCreateResponse();
//        response.setId((int) (System.currentTimeMillis() / 1000));
//        return response;
//    }

}
