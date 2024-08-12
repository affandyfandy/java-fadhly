package com.example.fpt_midterm_pos.repository;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.fpt_midterm_pos.data.repository.ProductRepository;
import com.example.fpt_midterm_pos.data.model.Product;
import com.example.fpt_midterm_pos.data.model.Status;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    private Product prod1;
    private Product prod2;

    @BeforeEach
    void init() {
        prod1 = new Product();
        prod1.setName("Laptop");
        prod1.setPrice(150.99);
        prod1.setStatus(Status.Active);
        prod1.setQuantity(10);
        prod1.setCreatedAt(new Date());
        prod1.setUpdatedAt(new Date());
        productRepository.save(prod1);

        prod2 = new Product();
        prod2.setName("Camera");
        prod2.setPrice(100.99);
        prod2.setStatus(Status.Deactive);
        prod2.setQuantity(20);
        prod2.setCreatedAt(new Date());
        prod2.setUpdatedAt(new Date());
        productRepository.save(prod2);
    }

    @Test
    void productRepository_findByFiltersPages_returnAllProductByFiltersPages() {
        Sort sort = Sort.unsorted();

        // Sort nameSort = Sort.by("name");
        // nameSort = nameSort.ascending();
        // sort = sort.and(nameSort);
        // Sort priceSort = Sort.by("price");
        // priceSort = priceSort.ascending();
        // sort = sort.and(priceSort);

        Pageable sortedPageable = PageRequest.of(0, 20, sort);
        Page<Product> prods = productRepository.findByFilters(
            Status.Active,
            null,
            null,
            null,
            sortedPageable
        );

        Assertions.assertNotNull(prods);
        Assertions.assertEquals(0, prods.getNumber());
        Assertions.assertEquals(20, prods.getSize());
        Assertions.assertTrue(prods.getTotalElements() == 1);
    }

    @Test
    void productRrepository_findProductById_returnProduct() {
        UUID id = prod1.getId();
        Product prod = productRepository.findById(id).orElse(null);

        Assertions.assertNotNull(prod);
        Assertions.assertEquals(id, prod.getId());
        Assertions.assertEquals("Laptop", prod.getName());
        Assertions.assertEquals(150.99, prod.getPrice());
        Assertions.assertEquals(Status.Active, prod.getStatus());
        Assertions.assertEquals(10, prod.getQuantity());
        Assertions.assertTrue(prod.getCreatedAt().before(new Date()));
        Assertions.assertTrue(prod.getUpdatedAt().before(new Date()));
    }

    @Test
    void productRepository_saveCreateProduct_returnProduct() {
        Product prod = new Product();
        prod.setName("Bag");
        prod.setPrice(45.99);
        prod.setStatus(Status.Active);
        prod.setQuantity(15);
        prod.setCreatedAt(new Date());
        prod.setUpdatedAt(new Date());
        
        Product savedProd = productRepository.save(prod);

        Assertions.assertNotNull(savedProd);
        Assertions.assertNotNull(savedProd.getId());
        Assertions.assertEquals("Bag", savedProd.getName());
        Assertions.assertEquals(45.99, savedProd.getPrice());
        Assertions.assertEquals(Status.Active, savedProd.getStatus());
        Assertions.assertEquals(15, savedProd.getQuantity());
        Assertions.assertTrue(savedProd.getCreatedAt().before(new Date()));
        Assertions.assertTrue(savedProd.getUpdatedAt().before(new Date()));
    }

    @Test
    void productRerpository_saveUpdateProduct_returnUpdatedProduct() {
        UUID id = prod1.getId();
        Product prod = productRepository.findById(id).orElse(null);
        prod.setName("Laptop Gaming");
        prod.setPrice(199.99);
        prod.setQuantity(13);
        prod.setUpdatedAt(new Date());

        Product updatedProd = productRepository.save(prod);

        Assertions.assertNotNull(updatedProd);
        Assertions.assertEquals(id, updatedProd.getId());
        Assertions.assertEquals("Laptop Gaming", updatedProd.getName());
        Assertions.assertEquals(199.99, updatedProd.getPrice());
        Assertions.assertEquals(13, updatedProd.getQuantity());
        Assertions.assertTrue(updatedProd.getUpdatedAt().after(updatedProd.getCreatedAt()));
    }

    @Test
    void productRepository_saveUpdateProductStatus_returnUpdateCustomerStatus() {
        UUID id = prod1.getId();
        Product prod = productRepository.findById(id).orElse(null);
        prod.setStatus(Status.Deactive);
        prod.setUpdatedAt(new Date());

        Product updatedProd = productRepository.save(prod);

        Assertions.assertNotNull(updatedProd);
        Assertions.assertEquals(id, updatedProd.getId());
        Assertions.assertEquals(Status.Deactive, updatedProd.getStatus());
        Assertions.assertTrue(updatedProd.getUpdatedAt().after(updatedProd.getCreatedAt()));
    }

    @Test
    void productRepository_saveAllProducts_returnAllProducts() {
        List<Product> prods = new ArrayList<>();
        prod1 = new Product();
        prod1.setName("Tumbler");
        prod1.setPrice(25.99);
        prod1.setStatus(Status.Active);
        prod1.setQuantity(10);
        prod1.setCreatedAt(new Date());
        prod1.setUpdatedAt(new Date());
        prods.add(prod1);

        prod2 = new Product();
        prod2.setName("Chair");
        prod2.setPrice(70.99);
        prod2.setStatus(Status.Active);
        prod2.setQuantity(20);
        prod2.setCreatedAt(new Date());
        prod2.setUpdatedAt(new Date());
        prods.add(prod2);

        List<Product> savedProds = productRepository.saveAll(prods);

        Assertions.assertNotNull(savedProds);
        Assertions.assertEquals(2, savedProds.size());

        List<Product> getAllProds = productRepository.findAll();

        Assertions.assertEquals(4, getAllProds.size());
    }
}