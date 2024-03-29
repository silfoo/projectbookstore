package com.silvana.bookstore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * configuration class about error messages validating book.class
 */
@Configuration
public class MessageValidatorConfig {

    private final MessageSource messageSource;

    /**
     * MessageSource dependency injection by constructor
     */
    @Autowired
    public MessageValidatorConfig(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * configuration bean about error messages
     * @return error message the type LocalValidatorFactoryBean
     */
    @Bean
    public LocalValidatorFactoryBean getValidator(){
        LocalValidatorFactoryBean lvfb = new LocalValidatorFactoryBean();
        lvfb.setValidationMessageSource(messageSource);
        return lvfb;
    }



}
