package com.example.lecture15.assignment3.config;

import com.example.lecture15.assignment3.entity.ApiKey;
import com.example.lecture15.assignment3.service.ApiKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Component
public class ApiKeyInterceptor implements HandlerInterceptor {
    @Autowired
    private ApiKeyService apiKeyService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String apiKey = request.getHeader("api-key");

        if (apiKey == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Missing API Key");
            return false;
        }

        Optional<ApiKey> apiKeyOptional = apiKeyService.validateAndUpdateApiKey(apiKey);

        if (apiKeyOptional.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid API Key");
            return false;
        }

        ApiKey validApiKey = apiKeyOptional.get();
        request.setAttribute("username", validApiKey.getUsername());

        response.setHeader("source", "fpt-software");
        response.setHeader("timestamp", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        return true;
    }
}
