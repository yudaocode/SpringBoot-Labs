package cn.iocoder.springboot.lab30.rpc.api;

import cn.iocoder.springboot.lab30.rpc.dto.UserDTO;

public interface UserRpcService {

    UserDTO get(Integer id);

}
