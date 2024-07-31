package com.example.lecture15.assignment2.entity;

import jakarta.persistence.*;

@Entity
public class ApiKey {
    @Id
    private String apiKey;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}