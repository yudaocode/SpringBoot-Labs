package cn.iocoder.springboot.lab65.demo.client;

import cn.iocoder.springboot.lab65.demo.wsdl.UserCreateRequest;
import cn.iocoder.springboot.lab65.demo.wsdl.UserCreateResponse;
import cn.iocoder.springboot.lab65.demo.wsdl.UserGetRequest;
import cn.iocoder.springboot.lab65.demo.wsdl.UserGetResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class UserClient extends WebServiceGatewaySupport {

    public UserGetResponse getUser(Integer id) {
        // 创建请求对象
        UserGetRequest request = new UserGetRequest();
        request.setId(id);
        // 执行请求
        return (UserGetResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    }

    public UserCreateResponse createUser(String name, Integer gender) {
        // 创建请求对象
        UserCreateRequest request = new UserCreateRequest();
        request.setName(name);
        request.setGender(gender);
        // 执行请求
        return (UserCreateResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    }

}
