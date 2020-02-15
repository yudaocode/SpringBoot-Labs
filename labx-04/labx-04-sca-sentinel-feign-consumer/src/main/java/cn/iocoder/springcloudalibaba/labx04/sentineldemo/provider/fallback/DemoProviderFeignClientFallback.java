package cn.iocoder.springcloudalibaba.labx04.sentineldemo.provider.fallback;

import cn.iocoder.springcloudalibaba.labx04.sentineldemo.provider.feign.DemoProviderFeignClient;

public class DemoProviderFeignClientFallback implements DemoProviderFeignClient {

    private Throwable throwable;

    public DemoProviderFeignClientFallback(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public String echo() {
            return "fallback:" + throwable.getClass().getSimpleName();
    }

}
