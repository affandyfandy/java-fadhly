package com.example.lecture15.assignment1.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    
    @Bean
    public FilterRegistrationBean<RequestTimingFilter> requestTimingFilter() {
        FilterRegistrationBean<RequestTimingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RequestTimingFilter());
        registrationBean.addUrlPatterns("/api/v1/filter/*");
        return registrationBean;
    }
}
