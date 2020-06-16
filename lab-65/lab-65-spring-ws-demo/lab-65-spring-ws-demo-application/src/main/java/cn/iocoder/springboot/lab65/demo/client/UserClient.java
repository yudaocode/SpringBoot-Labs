package cn.iocoder.springboot.lab65.demo.client;

import cn.iocoder.springboot.lab65.demo.wsdl.UserGetRequest;
import cn.iocoder.springboot.lab65.demo.wsdl.UserGetResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class UserClient extends WebServiceGatewaySupport {

    public static final String WEB_SERVICES_URI = "http://127.0.0.1:8080/ws";

    private static final String WE_SERVICES_NAMESPACE = "https://github.com/YunaiV/SpringBoot-Labs/tree/master/lab-65/lab-65-spring-ws-demo";

    public UserGetResponse getUser(Integer id) {
        UserGetRequest request = new UserGetRequest();
        request.setId(id);
        return (UserGetResponse) getWebServiceTemplate().marshalSendAndReceive(
                request, new SoapActionCallback(WE_SERVICES_NAMESPACE + "/UserCreateRequest"));
    }

}
