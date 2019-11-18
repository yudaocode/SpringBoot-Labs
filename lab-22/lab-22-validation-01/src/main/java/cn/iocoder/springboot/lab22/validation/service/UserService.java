package cn.iocoder.springboot.lab22.validation.service;

import cn.iocoder.springboot.lab22.validation.dto.UserAddDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@Service
@Validated
public class UserService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public void get(@Min(value = 1L, message = "编号必须大于 0") Integer id) {
        logger.info("[get][id: {}]", id);
    }

    public void add(@Valid UserAddDTO addDTO) {
        logger.info("[add][addDTO: {}]", addDTO);
    }

    public void add01(UserAddDTO addDTO) {
        this.add(addDTO);
    }

    public void add02(UserAddDTO addDTO) {
        self().add(addDTO);
    }

    private UserService self() {
        return (UserService) AopContext.currentProxy();
    }

}
