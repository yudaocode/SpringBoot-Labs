package cn.iocoder.springboot.lab30.rpc.service;


import cn.iocoder.springboot.lab30.rpc.api.DemoUserRpcService;
import cn.iocoder.springboot.lab30.rpc.dto.DemoUserDTO;

public class DemoUserRpcServiceImpl implements DemoUserRpcService {

    public DemoUserDTO get(Integer id) {
        return new DemoUserDTO().setId(id)
                .setName("没有昵称：" + id)
                .setGender(id % 2 + 1); // 1 - 男；2 - 女
    }

}
