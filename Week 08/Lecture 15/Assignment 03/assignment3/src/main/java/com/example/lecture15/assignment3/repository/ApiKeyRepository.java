package com.example.lecture15.assignment3.repository;

import com.example.lecture15.assignment3.entity.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApiKeyRepository extends JpaRepository<ApiKey, String> {
    Optional<ApiKey> findByApiKeyAndUsername(String apiKey, String username);
}
