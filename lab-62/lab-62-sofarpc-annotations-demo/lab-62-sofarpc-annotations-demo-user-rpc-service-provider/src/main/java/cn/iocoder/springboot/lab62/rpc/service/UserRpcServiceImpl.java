package cn.iocoder.springboot.lab62.rpc.service;


import cn.iocoder.springboot.lab62.rpc.api.UserRpcService;
import cn.iocoder.springboot.lab62.rpc.dto.UserAddDTO;
import cn.iocoder.springboot.lab62.rpc.dto.UserDTO;
import com.alipay.sofa.runtime.api.annotation.SofaService;
import com.alipay.sofa.runtime.api.annotation.SofaServiceBinding;
import org.springframework.stereotype.Service;

@Service
@SofaService(bindings = @SofaServiceBinding(bindingType = "bolt"))
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
