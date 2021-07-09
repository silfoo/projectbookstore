package com.silvana.bookstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * configuration class of different languages
 *
 * @author silvana
 * @version 1.0
 */
@Configuration
public class MessageLanguageConfig implements WebMvcConfigurer {


    /**
     * the language was selected will be in the same session
     *
     * @return the local session with the language was selected
     */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.getDefault());
        return slr;
    }

    /**
     * method of the interceptor a local param in the language option
     *
     * @return what was the selected language
     */
    @Bean
    public LocaleChangeInterceptor localeInterceptor() {
        LocaleChangeInterceptor lchi = new LocaleChangeInterceptor();
        lchi.setIgnoreInvalidLocale(true);
        lchi.setParamName("language");
        return lchi;
    }

    /**
     * override of WebMvcConfigurer's method to implement the Bean localeInterceptor() with the param language
     *
     * @param registry, the interceptor registry, to add a new interceptor
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeInterceptor());
    }
}
