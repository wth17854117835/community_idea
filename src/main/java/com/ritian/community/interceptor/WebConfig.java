package com.ritian.community.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author: wangth_oup
 * @date: 2020-04-20 16:10
 * @description:
 **/
@Configuration
//去掉这个注解 就不会把static下的静态文件拦截了
//@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private SessionInterceptor sessionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionInterceptor).addPathPatterns("/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/**").addResourceLocations("classpath:/resource/","classpath:/static/");
//        super.addResourceHandlers(registry);
    }
}
