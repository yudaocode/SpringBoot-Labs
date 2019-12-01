package cn.iocoder.springboot.lab30.rpc.api;

import cn.iocoder.springboot.lab30.rpc.dto.DemoUserDTO;

public interface DemoUserRpcService {

    DemoUserDTO get(Integer id);

}
