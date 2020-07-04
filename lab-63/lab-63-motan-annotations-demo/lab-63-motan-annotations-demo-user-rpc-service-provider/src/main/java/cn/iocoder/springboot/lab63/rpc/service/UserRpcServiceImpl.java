package cn.iocoder.springboot.lab63.rpc.service;


import cn.iocoder.springboot.lab63.rpc.api.UserRpcService;
import cn.iocoder.springboot.lab63.rpc.dto.UserAddDTO;
import cn.iocoder.springboot.lab63.rpc.dto.UserDTO;
import com.weibo.api.motan.config.springsupport.annotation.MotanService;
import org.springframework.stereotype.Service;

@Service
@MotanService(export = "motan2:8001")
public class UserRpcServiceImpl implements UserRpcService {

    @Override
    public UserDTO get(Integer id) {
        return new UserDTO().setId(id)
                .setName("没有昵称：" + id)
                .setGender(id % 2 + 1); // 1 - 男；2 - 女
    }

    @Override
    public Integer add(UserAddDTO addDTO) {
        return (int) (System.currentTimeMillis() / 1000); // 嘿嘿，随便返回一个 id
    }

}
