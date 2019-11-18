package cn.iocoder.springboot.lab22.validation.config;

import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validator;

@Configuration
public class ValidationConfiguration {

    /**
     * 参考 {@link ValidationAutoConfiguration#defaultValidator()} 方法，构建 Validator Bean
     *
     * @return Validator 对象
     */
    @Bean
    public Validator validator(MessageSource messageSource)  {
        // 创建 LocalValidatorFactoryBean 对象
        LocalValidatorFactoryBean validator = ValidationAutoConfiguration.defaultValidator();
        // 设置 messageSource 属性，实现 i18 国际化
        validator.setValidationMessageSource(messageSource);
        // 返回
        return validator;
    }

}
