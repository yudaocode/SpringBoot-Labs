package cn.iocoder.springboot.lab65.userservice.config;

import org.springframework.boot.autoconfigure.webservices.WebServicesProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@Configuration
public class WebServiceConfig {

    public static final String NAMESPACE_URI = "https://github.com/YunaiV/SpringBoot-Labs/tree/master/lab-65/lab-65-spring-ws-demo";

    @Bean
    public XsdSchema usersSchema() {
        return new SimpleXsdSchema(new ClassPathResource("users.xsd"));
    }

	@Bean(name = "users")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema usersSchema, WebServicesProperties properties) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setLocationUri(properties.getPath());
		wsdl11Definition.setTargetNamespace(NAMESPACE_URI);
		wsdl11Definition.setSchema(usersSchema);
        wsdl11Definition.setPortTypeName("UsersPort");
        return wsdl11Definition;
	}

}
