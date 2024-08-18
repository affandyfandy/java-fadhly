package com.week10.authentication.service;

import com.week10.authentication.entity.Authentication;

public interface AuthenticationService {
    
    boolean validateApiKey(String apiKey);
    Authentication createApiKey(Authentication auth);
}
