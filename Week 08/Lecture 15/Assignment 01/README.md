# Assignment 01 - OncePerRequestFilter

## Task 01: Research OncePerRequestFilter in Spring

`oceperrequestfilter` is an abstarct class in Spring Boot Framework. This filter designed to ensure particular piece of code is executed only once per request, regardless of how many times the filter is called.

### Key Aspect of using OncePerRequestFilter

1. `Extends GenericFilterBean` -> It is the base class of filter type, that can initialize filter parameter from web application configuration data, and It is allows OncePerRequestFilter to be easily configured. Using `GenericFilterBean` will inherits several feature:
    - Automatic initialization
    - init() and destroy()
    - Access ServletContext
2. `Implements Filter Interface` -> It is part of the servlet API, that can be intercept requests before reach target resource and/or response has been generated. Key methods of using Filter Interface:
    - init(FilterConfig)
    - doFilter(ServletRequest, ServletResponse, FilterChain)
    - destroy()
3. `Guarantees Single Execution per Request` -> It is will ensure the method  is executed only once per request, regardless of how many times the filter is actually invoked. The ways to achive It:
    - Uses request attribute
    - Checks the attribute is exist or not, before executing filter logic
    - If doesn't exist, sets the attribute and executes the filter logic
    - If exist, skips the filter logic

### Pros using OncePerRequestFilter

1. `Simplicity`: Prevents duplicate processing and potential issues from multiple invocations in the same request
2. `Performance`: Avoid unnecessary repeated operations
3. `Flexibility`: Used for various cross-cutting concerns like logging, security checks, or altering the request or response

### Cons using OncePerRequestFilter

1. `Potential Overhead`: For simple operation, it might be overkill using filter
2. `Limited Control`: That should occur once per request, so it might be not suitable for the needs operation
3. `Order Dependency`: Ensure proper execution order when using multiple filter 

## Task 02: Example of OncePerRequestFilter

### Create Simple Project

1. `Filter Configuration Class`

```java
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
```

2. `Request Timing Filter Class`

```java
package com.example.lecture15.assignment1.config;

import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestTimingFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        long startTime = System.currentTimeMillis();

        try {
            filterChain.doFilter(request, response);
        } finally {
            long endTime = System.currentTimeMillis();
            long processingTime = endTime - startTime;
            logger.info("Request processing time: " + processingTime + " ms");
        }
    }
}
```

3. `Controleer Class`

```java
package com.example.lecture15.assignment1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/filter")
public class TestController {

    @GetMapping("/test")
    public String hello() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "Hello, World!";
    }
}
```

4. `Output`

![Result](img/Result%201.PNG)

![Result](img/Result%202.PNG)