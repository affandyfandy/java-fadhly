package com.week10.authentication.service.impl;

import com.week10.authentication.entity.Authentication;
import com.week10.authentication.repository.AuthenticationRepository;
import com.week10.authentication.service.AuthenticationService;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationRepository authenticationRepository;

    @Override
    public boolean validateApiKey(String apiKey) {
        return authenticationRepository.existsById(apiKey);
    }

    @Override
    public Authentication createApiKey(Authentication auth) {
        return authenticationRepository.save(auth);
    }
}
