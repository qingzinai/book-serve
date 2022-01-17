package com.example.bookServe.interceptorConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new Interceptors())
                //拦截所有的路径
                .addPathPatterns("/**");
                // 放行的路径
                //.excludePathPatterns("/api/user/login");
    }
}