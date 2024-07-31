package com.example.lecture15.assignment3.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ApiKey {
    @Id
    private String apiKey;
    private String username;
    private LocalDateTime lastUsed;

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLastUsed(LocalDateTime lastUsed) {
        this.lastUsed = lastUsed;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getUsername() {
        return username;
    }

    public LocalDateTime getLastUsed() {
        return lastUsed;
    }
}
