package com.example.fpt_midterm_pos.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.fpt_midterm_pos.data.model.Status;
import com.example.fpt_midterm_pos.dto.ProductDTO;
import com.example.fpt_midterm_pos.dto.ProductSaveDTO;
import com.example.fpt_midterm_pos.dto.ProductSearchCriteriaDTO;
import com.example.fpt_midterm_pos.dto.ProductShowDTO;
import com.example.fpt_midterm_pos.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @MockBean
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    private ProductDTO productDTO;
    private ProductDTO productDTO2;
    private ProductSaveDTO productSaveDTO;
    private ProductShowDTO productShowDTO;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
        objectMapper = new ObjectMapper();

        productDTO = new ProductDTO(UUID.randomUUID(), "Laptop", 159.99, Status.Active, 10);
        productDTO2 = new ProductDTO(UUID.randomUUID(), "Laptop", 159.99, Status.Deactive, 10);
        productSaveDTO = new ProductSaveDTO("Laptop", 159.99, 10);
        productShowDTO = new ProductShowDTO(UUID.randomUUID(), "Laptop", 159.99, 10);
    }

    @Test
    void productController_getProductsByCriteria_returnAllProducts() throws Exception {
        Pageable pageable = PageRequest.of(0, 20);
        ProductSearchCriteriaDTO crit = new ProductSearchCriteriaDTO();
        List<ProductShowDTO> productDTOs = List.of(productShowDTO);

        Page<ProductShowDTO> productPage = new PageImpl<>(productDTOs, pageable, productDTOs.size());

        when(productService.findByCriteria(crit, pageable)).thenReturn(productPage);

        String expectedResult = objectMapper.writeValueAsString(productPage);

        mockMvc.perform(get("/api/v1/products")
                .param("page", "0")
                .param("size", "20"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isNotEmpty())
                .andExpect(content().json(expectedResult));
    }

    @Test
    void productController_getProductsByCriteria_returnNoProducts() throws Exception {
        Pageable pageable = PageRequest.of(0, 20);
        ProductSearchCriteriaDTO crit = new ProductSearchCriteriaDTO();
        List<ProductShowDTO> productDTOs = List.of();

        Page<ProductShowDTO> productPage = new PageImpl<>(productDTOs, pageable, productDTOs.size());

        when(productService.findByCriteria(crit, pageable)).thenReturn(productPage);

        mockMvc.perform(get("/api/v1/products")
                .param("page", "0")
                .param("size", "20"))
                .andExpect(status().isNoContent());
    }

    @Test
    void productController_createProduct_returnProductDTO() throws Exception {
        when(productService.createProduct(productSaveDTO)).thenReturn(productDTO);

        String expectedInput = objectMapper.writeValueAsString(productSaveDTO);
        String expectedResult = objectMapper.writeValueAsString(productDTO);

        mockMvc.perform(post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(expectedInput))
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedResult))
                .andExpect(jsonPath("$.id").value(productDTO.getId().toString()));
    }

    @Test
    void productController_updateProduct_returnProductDTO() throws Exception {
        UUID productId = productDTO.getId();
        productDTO.setName("Laptop");
        productDTO.setPrice(150.99);
        productDTO.setQuantity(10);

        when(productService.updateProduct(productId, productSaveDTO)).thenReturn(productDTO);

        String expectedInput = objectMapper.writeValueAsString(productSaveDTO);
        String expectedResult = objectMapper.writeValueAsString(productDTO);

        mockMvc.perform(put("/api/v1/products/{id}", productId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(expectedInput))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResult))
                .andExpect(jsonPath("$.id").value(productDTO.getId().toString()))
                .andExpect(jsonPath("$.name").value(productDTO.getName().toString()))
                .andExpect(jsonPath("$.price").value(productDTO.getPrice().toString()))
                .andExpect(jsonPath("$.quantity").value(productDTO.getQuantity().toString()));
    }

    @Test
    void productController_updateProductStatusActive_returnProductDTO() throws Exception {
        UUID productId = productDTO2.getId();
        productDTO2.setStatus(Status.Active);

        when(productService.updateProductStatus(productId, Status.Active)).thenReturn(productDTO2);

        String expectedResult = objectMapper.writeValueAsString(productDTO2);

        mockMvc.perform(put("/api/v1/products/active/{id}", productId))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResult))
                .andExpect(jsonPath("$.id").value(productDTO2.getId().toString()))
                .andExpect(jsonPath("$.status").value(productDTO2.getStatus().toString()));
    }

    @Test
    void productController_updateProductStatusDeactive_returnProductDTO() throws Exception {
        UUID productId = productDTO.getId();
        productDTO.setStatus(Status.Deactive);

        when(productService.updateProductStatus(productId, Status.Deactive)).thenReturn(productDTO);

        String expectedResult = objectMapper.writeValueAsString(productDTO);

        mockMvc.perform(put("/api/v1/products/deactive/{id}", productId))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResult))
                .andExpect(jsonPath("$.id").value(productDTO.getId().toString()))
                .andExpect(jsonPath("$.status").value(productDTO.getStatus().toString()));
    }

    @Test
    void productController_uploadCSV_returnListProductDTO() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "test.csv", "text/csv", "name,price,quantity\nLaptop,159.99,10".getBytes());
        List<ProductDTO> productDTOs = List.of(productDTO);

        when(productService.saveProductsFromCSV(file)).thenReturn(productDTOs);

        String expectedResult = objectMapper.writeValueAsString(productDTOs);

        mockMvc.perform(multipart("/api/v1/products/upload")
                .file(file))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(content().json(expectedResult));
    }
}
