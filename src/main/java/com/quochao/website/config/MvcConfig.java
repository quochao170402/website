package com.quochao.website.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
////        registry.addViewController("/login").setViewName("login");
////        registry.addViewController("/home").setViewName("home");
//    }
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
////        if (!registry.hasMappingForPattern("/assets/**")) {
////            registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");
////        }
//    }
}
