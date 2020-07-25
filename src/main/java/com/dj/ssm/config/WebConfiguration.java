package com.dj.ssm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Autowired
    private MyInterceptor myInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {

        InterceptorRegistration interruptedException = registry.addInterceptor(myInterceptor);

        //拦截请求
        interruptedException.addPathPatterns("/**");
        //放过请求
        interruptedException.excludePathPatterns("/static/**");
        interruptedException.excludePathPatterns("/user/*");


    }

}
