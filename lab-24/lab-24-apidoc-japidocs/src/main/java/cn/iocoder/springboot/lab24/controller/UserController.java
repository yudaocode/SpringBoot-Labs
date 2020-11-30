package cn.iocoder.springboot.lab24.controller;

import cn.iocoder.springboot.lab24.vo.UserCreateReqVO;
import cn.iocoder.springboot.lab24.vo.UserListReqVO;
import cn.iocoder.springboot.lab24.vo.UserRespVO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户 API
 */
@RestController
@RequestMapping("/api/user/")
public class UserController {


    /**
     * 获得用户列表
     *
     * @param listReqVO 列表筛选条件
     * @return 用户列表
     */
    @GetMapping("list")
    public List<UserRespVO> list(UserListReqVO listReqVO){
        return null;
    }

    /**
     * 保存用户
     *
     * @param createReqVO 创建用户信息
     * @return 用户编号
     */
    @PostMapping("save")
    public Integer saveUser(@RequestBody UserCreateReqVO createReqVO){
        return 1;
    }

    /**
     * 删除指定编号的用户
     *
     * @param id 用户编号
     * @return 是否成功
     */
    @DeleteMapping("delete")
    public Boolean deleteUser(@RequestParam Long id){
        return true;
    }


}
