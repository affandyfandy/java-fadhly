package com.example.fpt_midterm_pos.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.fpt_midterm_pos.data.model.Product;
import com.example.fpt_midterm_pos.data.model.Status;
import com.example.fpt_midterm_pos.dto.ProductDTO;
import com.example.fpt_midterm_pos.dto.ProductSaveDTO;
import com.example.fpt_midterm_pos.dto.ProductShowDTO;
import com.example.fpt_midterm_pos.mapper.ProductMapper;

import org.mapstruct.factory.Mappers;

import java.util.*;

public class ProductMapperTest {

    private ProductMapper productMapper;
    
    private Product product;
    private ProductDTO productDTO;
    private ProductShowDTO productShowDTO;
    private ProductSaveDTO productSaveDTO;

    private List<ProductSaveDTO> productSaveDTOs;
    private List<Product> products;

    @BeforeEach
    void init() {
        productMapper = Mappers.getMapper(ProductMapper.class);

        product = new Product(UUID.randomUUID(), "Laptop", 159.99, Status.Active, 10, new Date(), new Date());
        productDTO = new ProductDTO(UUID.randomUUID(), "Laptop", 159.99, Status.Deactive, 10);
        productShowDTO = new ProductShowDTO(UUID.randomUUID(), "Laptop", 159.99, 10);
        productSaveDTO = new ProductSaveDTO("Laptop", 159.99, 10);

        products = List.of(product);
        productSaveDTOs = List.of(productSaveDTO);
    }

    @Test
    void productMapper_toProductDTO_returnProductDTO() {
        ProductDTO prodDTO = productMapper.toProductDTO(product);

        Assertions.assertEquals(product.getId(), prodDTO.getId());
        Assertions.assertEquals(product.getName(), prodDTO.getName());
        Assertions.assertEquals(product.getPrice(), prodDTO.getPrice());
        Assertions.assertEquals(product.getStatus(), prodDTO.getStatus());
        Assertions.assertEquals(product.getQuantity(), prodDTO.getQuantity());
    }

    @Test
    void productMapper_toProductFromProductDTO_returnProduct() {
        Product prod = productMapper.toProduct(productDTO);

        Assertions.assertEquals(productDTO.getId(), prod.getId());
        Assertions.assertEquals(productDTO.getName(), prod.getName());
        Assertions.assertEquals(productDTO.getPrice(), prod.getPrice());
        Assertions.assertEquals(productDTO.getStatus(), prod.getStatus());
        Assertions.assertEquals(productDTO.getQuantity(), prod.getQuantity());
        Assertions.assertNull(prod.getCreatedAt());
        Assertions.assertNull(prod.getUpdatedAt());
    }

    @Test
    void productMapper_toProductShowDTO_returnProductShowDTO() {
        ProductShowDTO prodShowDTO = productMapper.toShowDTO(product);

        Assertions.assertEquals(product.getId(), prodShowDTO.getId());
        Assertions.assertEquals(product.getName(), prodShowDTO.getName());
        Assertions.assertEquals(product.getPrice(), prodShowDTO.getPrice());
        Assertions.assertEquals(product.getQuantity(), prodShowDTO.getQuantity());
    }

    @Test
    void productMapper_toProductFromProductShowDTO_returnProduct() {
        Product prod = productMapper.toProduct(productShowDTO);

        Assertions.assertEquals(productShowDTO.getId(), prod.getId());
        Assertions.assertEquals(productShowDTO.getName(), prod.getName());
        Assertions.assertEquals(productShowDTO.getPrice(), prod.getPrice());
        Assertions.assertEquals(productShowDTO.getQuantity(), prod.getQuantity());
        Assertions.assertEquals(Status.Active, prod.getStatus());
        Assertions.assertNull(prod.getCreatedAt());
        Assertions.assertNull(prod.getUpdatedAt());
    }

    @Test
    void productMapper_toProductSaveDTO_returnProductSaveDTO() {
        ProductSaveDTO prodSaveDTO = productMapper.toProductSaveDTO(product);

        Assertions.assertEquals(product.getName(), prodSaveDTO.getName());
        Assertions.assertEquals(product.getPrice(), prodSaveDTO.getPrice());
        Assertions.assertEquals(product.getQuantity(), prodSaveDTO.getQuantity());
    }

    @Test
    void productMapper_toProductFromProductSaveDTO_returnProduct() {
        Product prod = productMapper.toProduct(productSaveDTO);

        Assertions.assertEquals(productShowDTO.getName(), prod.getName());
        Assertions.assertEquals(productShowDTO.getPrice(), prod.getPrice());
        Assertions.assertEquals(productShowDTO.getQuantity(), prod.getQuantity());
        Assertions.assertEquals(Status.Active, prod.getStatus());
        Assertions.assertNull(prod.getId());
        Assertions.assertNull(prod.getCreatedAt());
        Assertions.assertNull(prod.getUpdatedAt());
    }

    @Test
    void productMapper_toProductDTOListFromListProduct_returnListProductDTO() {
        List<ProductDTO> prodDTOs = productMapper.toProductDTOList(products);

        Assertions.assertEquals(products.size(), prodDTOs.size());

        for (int i = 0; i < products.size(); i++) {
            Assertions.assertEquals(products.get(i).getId(), prodDTOs.get(i).getId());
            Assertions.assertEquals(products.get(i).getName(), prodDTOs.get(i).getName());
            Assertions.assertEquals(products.get(i).getPrice(), prodDTOs.get(i).getPrice());
            Assertions.assertEquals(products.get(i).getStatus(), prodDTOs.get(i).getStatus());
            Assertions.assertEquals(products.get(i).getQuantity(), prodDTOs.get(i).getQuantity());
        }
    }

    @Test
    void productMapper_toListProductFromListProductSaveDTO_returnListProduct() {
        List<Product> prods = productMapper.toProductList(productSaveDTOs);

        Assertions.assertEquals(productSaveDTOs.size(), prods.size());

        for (int i = 0; i < productSaveDTOs.size(); i++) {
            Assertions.assertEquals(productSaveDTOs.get(i).getName(), prods.get(i).getName());
            Assertions.assertEquals(productSaveDTOs.get(i).getPrice(), prods.get(i).getPrice());
            Assertions.assertEquals(productSaveDTOs.get(i).getQuantity(), prods.get(i).getQuantity());
            Assertions.assertEquals(Status.Active, prods.get(i).getStatus());
            Assertions.assertNull(prods.get(i).getId());
            Assertions.assertNull(prods.get(i).getCreatedAt());
            Assertions.assertNull(prods.get(i).getUpdatedAt());
        }

        
    }
}
