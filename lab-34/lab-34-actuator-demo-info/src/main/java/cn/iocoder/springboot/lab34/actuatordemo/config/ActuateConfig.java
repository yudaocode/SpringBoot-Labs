package cn.iocoder.springboot.lab34.actuatordemo.config;

import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.boot.actuate.info.SimpleInfoContributor;
import org.springframework.context.annotation.Bean;

import java.util.Collections;

//@Configuration
public class ActuateConfig {

    @Bean
    public InfoContributor exampleInfo() {
        return new SimpleInfoContributor("example",
                Collections.singletonMap("key", "value"));
    }

}
