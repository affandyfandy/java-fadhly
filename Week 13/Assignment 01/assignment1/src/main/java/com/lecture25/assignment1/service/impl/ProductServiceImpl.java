package com.lecture25.assignment1.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lecture25.assignment1.entity.Product;
import com.lecture25.assignment1.repository.ProductRepository;
import com.lecture25.assignment1.service.ProductService;

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
        Product checkProd = getProductById(id);
        if(checkProd != null) {
            checkProd.setName(product.getName());
            checkProd.setPrice(product.getPrice());
            checkProd.setQuantity(product.getQuantity());
            checkProd.setStatus("Active");
            checkProd.setCreateAt(new Date());
            checkProd.setUpdatedAt(new Date());

            return productRepository.save(checkProd);
        }
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
