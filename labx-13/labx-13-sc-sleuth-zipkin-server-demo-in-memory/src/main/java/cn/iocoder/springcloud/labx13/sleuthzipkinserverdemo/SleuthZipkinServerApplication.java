package cn.iocoder.springcloud.labx13.sleuthzipkinserverdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin2.server.internal.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
public class SleuthZipkinServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SleuthZipkinServerApplication.class, args);
    }

}
