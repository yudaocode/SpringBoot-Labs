package cn.iocoder.springcloudalibaba.labx04.sentineldemo.provider.web;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@ControllerAdvice(basePackages = "cn.iocoder.springcloudalibaba.labx04.sentineldemo.provider")
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = BlockException.class)
    public String blockExceptionHandler(BlockException blockException) {
        return "请求被拦截，拦截类型为 " + blockException.getClass().getSimpleName();
    }

}
