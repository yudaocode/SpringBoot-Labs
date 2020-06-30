package catdemo.config;

import cn.iocoder.springboot.lab61.cat.opentracing.CatTracer;
import io.opentracing.Tracer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZipkinConfig {

    @Bean
    public Tracer openTracer() {
        return new CatTracer();
    }

}
