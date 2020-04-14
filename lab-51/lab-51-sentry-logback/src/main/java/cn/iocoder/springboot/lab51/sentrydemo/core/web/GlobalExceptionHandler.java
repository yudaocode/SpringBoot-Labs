package cn.iocoder.springboot.lab51.sentrydemo.core.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice(basePackages = "cn.iocoder.springboot.lab51.sentrydemo.controller")
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    // ... 省略其它类型异常的处理

    /**
     * 处理其它 Exception 异常
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public String exceptionHandler(HttpServletRequest req, Exception e) {
        // 记录异常日志
        logger.error("[exceptionHandler]", e);
        // 返回 ERROR CommonResult
        return "系统异常";
    }

}
