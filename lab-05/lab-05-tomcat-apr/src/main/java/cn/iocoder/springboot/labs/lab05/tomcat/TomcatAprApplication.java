package cn.iocoder.springboot.labs.lab05.tomcat;

import org.apache.catalina.core.AprLifecycleListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TomcatAprApplication {

    @Bean
    public ServletWebServerFactory servletWebServerFactory() {
        TomcatServletWebServerFactory tomcatServletWebServerFactory = new TomcatServletWebServerFactory();
        tomcatServletWebServerFactory.setProtocol("org.apache.coyote.http11.Http11AprProtocol");
        tomcatServletWebServerFactory.addContextLifecycleListeners(new AprLifecycleListener());
        return tomcatServletWebServerFactory;
    }

    // Mac 下，VM 启动参数，需要增加 -Djava.library.path=/usr/local/opt/tomcat-native/lib 。
    // 当然是在安装了 apr 的情况。安装命令是 brew install apr tomcat-native
    public static void main(String[] args) {
        SpringApplication.run(TomcatAprApplication.class);
    }

}
