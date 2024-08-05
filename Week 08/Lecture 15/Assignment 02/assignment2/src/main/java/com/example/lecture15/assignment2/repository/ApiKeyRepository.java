package com.example.lecture15.assignment2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lecture15.assignment2.entity.ApiKey;

public interface ApiKeyRepository extends JpaRepository<ApiKey, String> {

}
