package com.yuji.contentcore.config;


import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

/**
 * 资源文件配置加载
 *
 * @author ruoyi
 */
@Configuration
public class I18nConfig implements WebMvcConfigurer
{

    @Bean
    public MessageSource messageSource() {
        // 多语言文件地址
        Locale.setDefault(Locale.CHINA);
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        //设置国际化文件存储路径   resources目录下 可以设置多个
        messageSource.addBasenames("i18n/messages");
        //设置根据key如果没有获取到对应的文本信息,则返回key作为信息
        messageSource.setUseCodeAsDefaultMessage(true);
        //设置字符编码
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.toString());
        return messageSource;
    }
    @Bean
    public LocaleResolver localeResolver()
    {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        // 默认语言
        slr.setDefaultLocale(Locale.CHINA);
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor()
    {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        // 参数名
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(localeChangeInterceptor());
    }
}