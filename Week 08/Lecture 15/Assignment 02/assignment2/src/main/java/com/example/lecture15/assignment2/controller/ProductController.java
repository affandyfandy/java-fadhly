package com.example.lecture15.assignment2.controller;

import com.example.lecture15.assignment2.service.ProductService;
import com.example.lecture15.assignment2.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> prd = productService.getAllProducts();
        if(prd.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(prd);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product prd = productService.getProductById(id);
        if(prd == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(prd);
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product prd = productService.updateProduct(id, product);
        if(prd == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(prd);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        Product prd = productService.getProductById(id);
        if(prd == null) {
            return ResponseEntity.notFound().build();
        }
        productService.deleteProduct(id);
        return ResponseEntity.ok().<Void>build();
    }
}
