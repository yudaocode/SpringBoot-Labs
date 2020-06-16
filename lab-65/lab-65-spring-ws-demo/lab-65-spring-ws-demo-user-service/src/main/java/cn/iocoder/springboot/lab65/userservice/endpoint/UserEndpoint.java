package cn.iocoder.springboot.lab65.userservice.endpoint;

import cn.iocoder.springboot.lab65.userservice.config.WebServicesConfig;
import cn.iocoder.springboot.lab65.userservice.model.UserCreateRequest;
import cn.iocoder.springboot.lab65.userservice.model.UserCreateResponse;
import cn.iocoder.springboot.lab65.userservice.model.UserGetRequest;
import cn.iocoder.springboot.lab65.userservice.model.UserGetResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class UserEndpoint {

	@PayloadRoot(namespace = WebServicesConfig.NAMESPACE_URI, localPart = "UserGetRequest")
	@ResponsePayload
	public UserGetResponse get(@RequestPayload UserGetRequest request) {
        UserGetResponse response = new UserGetResponse();
        response.setId(request.getId());
        response.setName("没有昵称：" + request.getId());
        response.setGender(request.getId() % 2 + 1);
		return response;
	}

    @PayloadRoot(namespace = WebServicesConfig.NAMESPACE_URI, localPart = "UserCreateRequest")
    @ResponsePayload
    public UserCreateResponse create(@RequestPayload UserCreateRequest request) {
        UserCreateResponse response = new UserCreateResponse();
        response.setId((int) (System.currentTimeMillis() / 1000));
        return response;
    }

}
