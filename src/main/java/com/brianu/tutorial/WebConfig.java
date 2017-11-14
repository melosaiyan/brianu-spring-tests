package com.brianu.tutorial;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.Locale;

/**
 * @author brian.urbina
 * This is the configuration file in place of the web.xml
 */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.brianu.tutorial.controller" })
public class WebConfig extends WebMvcConfigurerAdapter {

   @Bean
   public InternalResourceViewResolver resolver() {
      InternalResourceViewResolver resolver = new InternalResourceViewResolver();
      resolver.setViewClass(JstlView.class);
      resolver.setPrefix("/WEB-INF/views/");
      resolver.setSuffix(".jsp");
      return resolver;
   }
   
   /* Localization section is started */

   @Override
   public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(localeChangeInterceptor());
   }

   @Bean
   public LocaleChangeInterceptor localeChangeInterceptor(){
       LocaleChangeInterceptor localeChangeInterceptor=new LocaleChangeInterceptor();
       localeChangeInterceptor.setParamName("language");
       return localeChangeInterceptor;
   }

   @Bean(name = "localeResolver")
   public LocaleResolver getLocaleResolver(){
       return new CookieLocaleResolver();
   }
   

   @Bean
   public MessageSource messageSource() {
      ResourceBundleMessageSource source = new ResourceBundleMessageSource();
      source.setBasename("messages");
      source.setDefaultEncoding("UTF-8");
      return source;
   }
}