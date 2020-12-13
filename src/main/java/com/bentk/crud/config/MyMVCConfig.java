package com.bentk.crud.config;

import com.bentk.crud.component.LoginHandlerInterceptor;
import com.bentk.crud.component.MyLocaleResolver;
import com.bentk.crud.controller.LoginController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.xml.ws.handler.LogicalHandler;

/**
 * @author Ben
 * @date 2020-12-12 16:36
 */
//extends WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter
//@EnableWebMvc
@Configuration
public class MyMVCConfig implements WebMvcConfigurer  {

    /**
     * 配置web路径
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/bentk").setViewName("success");
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/index.html").setViewName("login");
        registry.addViewController("/main.html").setViewName("dashboard");




    }

    /**
     * 过滤资源路径
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/webjars/**") .addResourceLocations( "classpath:/META-INF/resources/webjars/");

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor( new LoginHandlerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/index.html","/","/user/login","/asserts/**","/webjars/**","/emp/**");
    }


    /**
     * i18n参数
     * @return
     */
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }
}

