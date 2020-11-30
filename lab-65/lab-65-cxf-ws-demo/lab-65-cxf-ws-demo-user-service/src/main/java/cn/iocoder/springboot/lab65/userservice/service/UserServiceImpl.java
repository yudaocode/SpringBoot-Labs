package cn.iocoder.springboot.lab65.userservice.service;

import cn.iocoder.springboot.lab65.userservice.config.CXFConfig;
import cn.iocoder.springboot.lab65.userservice.request.UserCreateRequest;
import cn.iocoder.springboot.lab65.userservice.request.UserGetRequest;
import cn.iocoder.springboot.lab65.userservice.response.UserCreateResponse;
import cn.iocoder.springboot.lab65.userservice.response.UserGetResponse;
import org.springframework.stereotype.Service;

import javax.jws.WebService;

@Service
@WebService(
        serviceName = "userService", // 服务名称
        targetNamespace = CXFConfig.NAMESPACE_URI // Namespace 命名空间
)
public class UserServiceImpl implements UserService {

    @Override
    public UserGetResponse get(UserGetRequest request) {
        UserGetResponse response = new UserGetResponse();
        response.setId(request.getId());
        response.setName("没有昵称：" + request.getId());
        response.setGender(request.getId() % 2 + 1);
        return response;
    }

    @Override
    public UserCreateResponse create(UserCreateRequest request) {
        UserCreateResponse response = new UserCreateResponse();
        response.setId((int) (System.currentTimeMillis() / 1000));
        return response;
    }

}
