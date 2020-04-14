package cn.iocoder.springboot.lab34.actuatordemo.demo;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class DemoInfoContributor implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("demo",
                Collections.singletonMap("key", "value"));
    }

}
