package com.lecture25.assignment1.service;

import java.util.List;

import com.lecture25.assignment1.entity.Product;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product createProduct(Product product);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
}
