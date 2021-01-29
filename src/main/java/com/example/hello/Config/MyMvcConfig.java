package com.example.hello.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/main").setViewName("index");
        registry.addViewController("/signUp").setViewName("registered");
        registry.addViewController("/project").setViewName("viewIndex");
        registry.addViewController("/judge").setViewName("judge");
        registry.addViewController("/vote").setViewName("vote");
        registry.addViewController("/voteChoose").setViewName("voteChoose");
        registry.addViewController("/view").setViewName("view");
        registry.addViewController("/password").setViewName("password");
    }
}
