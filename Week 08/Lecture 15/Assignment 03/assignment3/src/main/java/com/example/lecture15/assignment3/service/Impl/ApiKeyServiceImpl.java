package com.example.lecture15.assignment3.service.Impl;

import com.example.lecture15.assignment3.entity.ApiKey;
import com.example.lecture15.assignment3.service.ApiKeyService;
import com.example.lecture15.assignment3.repository.ApiKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ApiKeyServiceImpl implements ApiKeyService {
    
    @Autowired
    private ApiKeyRepository apiKeyRepository;

    @Transactional
    @Override
    public Optional<ApiKey> validateAndUpdateApiKey(String apiKey) {
        Optional<ApiKey> apiKeyOptional = apiKeyRepository.findByApiKey(apiKey);
        apiKeyOptional.ifPresent(key -> {
            key.setLastUsed(LocalDateTime.now());
            apiKeyRepository.save(key);
        });
        return apiKeyOptional;
    }
}
