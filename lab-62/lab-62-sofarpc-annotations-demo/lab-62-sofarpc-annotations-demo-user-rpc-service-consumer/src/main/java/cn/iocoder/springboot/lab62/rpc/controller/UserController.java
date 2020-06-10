package cn.iocoder.springboot.lab62.rpc.controller;

import cn.iocoder.springboot.lab62.rpc.api.UserRpcService;
import cn.iocoder.springboot.lab62.rpc.dto.UserAddDTO;
import cn.iocoder.springboot.lab62.rpc.dto.UserDTO;
import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.alipay.sofa.runtime.api.annotation.SofaReferenceBinding;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @SofaReference(binding = @SofaReferenceBinding(bindingType = "bolt"))
    private UserRpcService userRpcService;

    @GetMapping("/get")
    public UserDTO get(@RequestParam("id") Integer id) {
        return userRpcService.get(id);
    }

    @GetMapping("/add") // 为了方便测试，实际使用 @PostMapping
    public Integer add(@RequestParam("name") String name,
                       @RequestParam("gender") Integer gender) {
        UserAddDTO addDTO = new UserAddDTO().setName(name).setGender(gender);
        return userRpcService.add(addDTO);
    }

}
