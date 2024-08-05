package com.example.lecture15.assignment2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lecture15.assignment2.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
