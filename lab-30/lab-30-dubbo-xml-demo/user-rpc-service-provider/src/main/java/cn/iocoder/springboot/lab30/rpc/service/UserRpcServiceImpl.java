package cn.iocoder.springboot.lab30.rpc.service;


import cn.iocoder.springboot.lab30.rpc.api.UserRpcService;
import cn.iocoder.springboot.lab30.rpc.core.ServiceException;
import cn.iocoder.springboot.lab30.rpc.core.ServiceExceptionEnum;
import cn.iocoder.springboot.lab30.rpc.dto.UserAddDTO;
import cn.iocoder.springboot.lab30.rpc.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class UserRpcServiceImpl implements UserRpcService {

    @Override
    public UserDTO get(Integer id) {
        return new UserDTO().setId(id)
                .setName("没有昵称：" + id)
                .setGender(id % 2 + 1); // 1 - 男；2 - 女
    }

    @Override
    public Integer add(UserAddDTO addDTO) {
        // 这里，模拟用户已经存在的情况
        if ("yudaoyuanma".equals(addDTO.getName())) {
            throw new ServiceException(ServiceExceptionEnum.USER_EXISTS);
        }
        return (int) (System.currentTimeMillis() / 1000); // 嘿嘿，随便返回一个 id
    }

}
