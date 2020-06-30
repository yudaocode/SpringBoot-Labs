package cn.iocoder.springcloudalibaba.labx7.providerdemo.service;

import cn.iocoder.springcloudalibaba.labx7.api.UserService;
import cn.iocoder.springcloudalibaba.labx7.dto.UserAddDTO;
import cn.iocoder.springcloudalibaba.labx7.dto.UserDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@org.apache.dubbo.config.annotation.Service(version = "1.0.0", protocol = {"dubbo", "rest"})
@Path("/user")
public class UserServiceImpl implements UserService {

    @Override
    @GET
    @Path("/get")
    @Produces(APPLICATION_JSON_VALUE)
    public UserDTO get(@QueryParam("id") Integer id) {
        return new UserDTO().setId(id)
                .setName("没有昵称：" + id)
                .setGender(id % 2 + 1); // 1 - 男；2 - 女
    }

    @Override
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Integer add(UserAddDTO addDTO) {
        return (int) (System.currentTimeMillis() / 1000); // 嘿嘿，随便返回一个 id
    }

}
