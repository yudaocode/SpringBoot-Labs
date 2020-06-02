package cn.iocoder.springcloud.labx21.zuuldemo;

import com.alibaba.csp.sentinel.adapter.gateway.zuul.fallback.BlockResponse;
import com.alibaba.csp.sentinel.adapter.gateway.zuul.fallback.ZuulBlockFallbackManager;
import com.alibaba.csp.sentinel.adapter.gateway.zuul.fallback.ZuulBlockFallbackProvider;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class CustomBlockFallbackProvider implements ZuulBlockFallbackProvider {

    @PostConstruct
    public void init() {
        ZuulBlockFallbackManager.registerProvider(this); // 注册到 ZuulBlockFallbackManager
    }

    public String getRoute() {
        return "*";
    }

    public BlockResponse fallbackResponse(String route, Throwable cause) {
        if (cause instanceof BlockException) {
            return new BlockResponse(429, "你被 Block 啦！", route);
        } else {
            return new BlockResponse(500, "系统异常", route);
        }
    }

}
