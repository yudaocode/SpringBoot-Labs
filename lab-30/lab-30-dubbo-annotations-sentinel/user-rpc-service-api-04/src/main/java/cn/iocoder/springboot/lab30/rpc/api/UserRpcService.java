package cn.iocoder.springboot.lab30.rpc.api;

import cn.iocoder.springboot.lab30.rpc.dto.UserDTO;

/**
 * 用户服务 RPC Service 接口
 */
public interface UserRpcService {

    /**
     * 根据指定用户编号，获得用户信息
     *
     * @param id 用户编号
     * @return 用户信息
     */
    UserDTO get(Integer id);

}
