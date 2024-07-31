package com.example.lecture15.assignment3.service;

import com.example.lecture15.assignment3.entity.ApiKey;

import java.util.Optional;

public interface ApiKeyService {
    Optional<ApiKey> validateAndUpdateApiKey(String apiKey);
}
