package cn.iocoder.springboot.lab22.validation.service;

import cn.iocoder.springboot.lab22.validation.Application;
import cn.iocoder.springboot.lab22.validation.dto.UserAddDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private Validator validator;

    @Test
    public void testGet() {
        userService.get(-1);
    }

    @Test
    public void testAdd() {
        UserAddDTO addDTO = new UserAddDTO();
        userService.add(addDTO);
    }

    @Test
    public void testAdd01() {
        UserAddDTO addDTO = new UserAddDTO();
        userService.add01(addDTO);
    }

    @Test
    public void testAdd02() {
        UserAddDTO addDTO = new UserAddDTO();
        userService.add02(addDTO);
    }

    @Test
    public void testValidator() {
        // 打印，查看 validator 的类型
        System.out.println(validator);

        // 创建 UserAddDTO 对象
        UserAddDTO addDTO = new UserAddDTO();
        // 校验
        Set<ConstraintViolation<UserAddDTO>> result = validator.validate(addDTO);
        // 打印校验结果
        for (ConstraintViolation<UserAddDTO> constraintViolation : result) {
            // 属性:消息
            System.out.println(constraintViolation.getPropertyPath() + ":" + constraintViolation.getMessage());
        }
    }

}
