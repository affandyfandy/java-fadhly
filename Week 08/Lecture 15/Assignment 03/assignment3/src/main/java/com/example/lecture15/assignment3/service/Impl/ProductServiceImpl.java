package com.example.lecture15.assignment3.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lecture15.assignment3.repository.ProductRepository;
import com.example.lecture15.assignment3.service.ProductService;
import com.example.lecture15.assignment3.entity.Product;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Product prd = getProductById(id);
        if(prd != null) {
            prd.setName(product.getName());
            prd.setPrice(product.getPrice());
            return productRepository.save(prd);
        }
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
