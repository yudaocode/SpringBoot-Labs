package cn.iocoder.springboot.lab24.apidoc.controller;

import cn.iocoder.springboot.lab24.apidoc.dto.UserAddDTO;
import cn.iocoder.springboot.lab24.apidoc.dto.UserUpdateDTO;
import cn.iocoder.springboot.lab24.apidoc.vo.UserVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//@RestController
@RequestMapping("/tests")
//@Api(tags = "用户 API 接口")
public class TestController {

    @GetMapping("/list")
    @ApiOperation(value = "查询用户列表", notes = "目前仅仅是作为测试，所以返回用户全列表")
    public List<UserVO> list() {
        // 查询列表
        List<UserVO> result = new ArrayList<>();
        result.add(new UserVO().setId(1).setUsername("yudaoyuanma"));
        result.add(new UserVO().setId(2).setUsername("woshiyutou"));
        result.add(new UserVO().setId(3).setUsername("chifanshuijiao"));
        // 返回列表
        return result;
    }

    @GetMapping("/get")
    @ApiOperation("获得指定用户编号的用户")
    @ApiImplicitParam(paramType = "query", dataType = "Integer", name = "id", value = "用户编号", required = true, example = "1024")
    public UserVO get(@RequestParam("id") Integer id) {
        // 查询并返回用户
        return new UserVO().setId(id).setUsername(UUID.randomUUID().toString());
    }

    @PostMapping("add")
    @ApiOperation("添加用户")
    public Integer add(UserAddDTO addDTO) {
        // 插入用户记录，返回编号
        Integer returnId = UUID.randomUUID().hashCode();
        // 返回用户编号
        return returnId;
    }

    @PostMapping("/update")
    @ApiOperation("更新指定用户编号的用户")
    public Boolean update(UserUpdateDTO updateDTO) {
        // 更新用户记录
        Boolean success = true;
        // 返回更新是否成功
        return success;
    }

    @PostMapping("/delete")
    @ApiOperation("删除指定用户编号的用户")
    @ApiImplicitParam(paramType = "query", dataTypeClass = Integer.class, name = "id", value = "用户编号", required = true, example = "1024")
    public Boolean delete(@RequestParam("id") Integer id) {
        // 删除用户记录
        Boolean success = false;
        // 返回是否更新成功
        return success;
    }

}
