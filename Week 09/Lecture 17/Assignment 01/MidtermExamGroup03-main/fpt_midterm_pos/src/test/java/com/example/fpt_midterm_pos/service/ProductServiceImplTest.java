package com.example.fpt_midterm_pos.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import com.example.fpt_midterm_pos.data.model.Product;
import com.example.fpt_midterm_pos.data.model.Status;
import com.example.fpt_midterm_pos.dto.ProductDTO;
import com.example.fpt_midterm_pos.dto.ProductSaveDTO;
import com.example.fpt_midterm_pos.dto.ProductSearchCriteriaDTO;
import com.example.fpt_midterm_pos.dto.ProductShowDTO;
import com.example.fpt_midterm_pos.mapper.ProductMapper;
import com.example.fpt_midterm_pos.exception.BadRequestException;
import com.example.fpt_midterm_pos.exception.DuplicateStatusException;
import com.example.fpt_midterm_pos.data.repository.ProductRepository;
import com.example.fpt_midterm_pos.service.impl.ProductServiceImpl;
import com.example.fpt_midterm_pos.utils.FileUtils;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.List;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
public class ProductServiceImplTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;

    @Mock
    ProductMapper productMapper;

    private Product prod1;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);

        prod1 = new Product();
        prod1.setId(UUID.randomUUID());
        prod1.setName("Laptop");
        prod1.setPrice(150.99);
        prod1.setStatus(Status.Active);
        prod1.setQuantity(10);
        prod1.setCreatedAt(new Date());
        prod1.setUpdatedAt(new Date());
    }

    @Test
    void productServiceImpl_findByCriteria_returnPagesAscProductShowDTO() {
        ProductSearchCriteriaDTO criteria = new ProductSearchCriteriaDTO();
        criteria.setName("Test Product");
        criteria.setSortByName("asc");
        criteria.setSortByPrice("asc");
        criteria.setMinPrice(100.0);
        criteria.setMaxPrice(200.0);

        ProductShowDTO productShowDTO = new ProductShowDTO();
        Product prod = new Product();

        Pageable pageable = PageRequest.of(0, 20);
        List<Product> productList = List.of(prod);
        Page<Product> productPage = new PageImpl<>(productList, pageable, productList.size());

        when(productRepository.findByFilters(any(), anyString(), any(), any(), any(Pageable.class))).thenReturn(productPage);
        when(productMapper.toShowDTO(prod)).thenReturn(productShowDTO);
        
        Page<ProductShowDTO> result = productService.findByCriteria(criteria, pageable);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.getTotalElements());
        Assertions.assertEquals(productShowDTO, result.getContent().get(0));

        ProductSearchCriteriaDTO criteria2 = new ProductSearchCriteriaDTO();
        criteria2.setName("Test Product");
        criteria2.setSortByName("desc");
        criteria2.setSortByPrice("desc");
        criteria2.setMinPrice(100.0);
        criteria2.setMaxPrice(200.0);

        Page<ProductShowDTO> result2 = productService.findByCriteria(criteria2, pageable);

        Assertions.assertNotNull(result2);
        Assertions.assertEquals(1, result2.getTotalElements());
        Assertions.assertEquals(productShowDTO, result2.getContent().get(0));
    }

    @Test
    void productServiceImpl_createProduct_returnCreateProduct() {
        ProductSaveDTO prodSaveDTO = new ProductSaveDTO();
        prodSaveDTO.setName("Bag");
        prodSaveDTO.setPrice(120.89);
        prodSaveDTO.setQuantity(10);

        Product prod = new Product();
        prod.setId(UUID.randomUUID());
        prod.setName(prodSaveDTO.getName());
        prod.setPrice(prodSaveDTO.getPrice());
        prod.setQuantity(prodSaveDTO.getQuantity());
        prod.setCreatedAt(new Date());
        prod.setUpdatedAt(new Date());

        ProductDTO prodDTO = new ProductDTO();
        prodDTO.setId(prod.getId());
        prodDTO.setName(prod.getName());
        prodDTO.setPrice(prod.getPrice());
        prodDTO.setStatus(prod.getStatus());
        prodDTO.setQuantity(prod.getQuantity());

        when(productMapper.toProduct(prodSaveDTO)).thenReturn(prod);
        when(productRepository.save(prod)).thenReturn(prod);
        when(productMapper.toProductDTO(prod)).thenReturn(prodDTO);

        ProductDTO result = productService.createProduct(prodSaveDTO);

        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getId());
        Assertions.assertEquals(prodSaveDTO.getName(), result.getName());
        Assertions.assertEquals(prodSaveDTO.getPrice(), result.getPrice());
        Assertions.assertEquals(prodSaveDTO.getQuantity(), result.getQuantity());
        Assertions.assertEquals(Status.Active, result.getStatus());
    }

    @Test
    void productServiceImpl_updateProduct_returnProductDTO() {
        UUID id = prod1.getId();

        ProductSaveDTO prodUpdateDTO = new ProductSaveDTO();
        prodUpdateDTO.setName("Backpack");
        prodUpdateDTO.setPrice(80.44);
        prodUpdateDTO.setQuantity(5);

        ProductDTO prodDTO = new ProductDTO();
        prodDTO.setId(prod1.getId());
        prodDTO.setName(prodUpdateDTO.getName());
        prodDTO.setPrice(prodUpdateDTO.getPrice());
        prodDTO.setStatus(prod1.getStatus());
        prodDTO.setQuantity(prodUpdateDTO.getQuantity());

        when(productRepository.findById(id)).thenReturn(Optional.of(prod1));
        when(productMapper.toProduct(prodUpdateDTO)).thenReturn(prod1);
        when(productRepository.save(prod1)).thenReturn(prod1);
        when(productMapper.toProductDTO(prod1)).thenReturn(prodDTO);

        ProductDTO result = productService.updateProduct(id, prodUpdateDTO);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(prod1.getId(), result.getId());
        Assertions.assertEquals(prodUpdateDTO.getName(), result.getName());
        Assertions.assertEquals(prodUpdateDTO.getPrice(), result.getPrice());
        Assertions.assertEquals(prodUpdateDTO.getQuantity(), result.getQuantity());
    }

    @Test
    void productServiceImpl_updateProductStatus_returnStatusActiveChangeProductDTO() {
        UUID id = prod1.getId();

        ProductDTO prodDTO = new ProductDTO();
        prodDTO.setId(prod1.getId());
        prodDTO.setName(prod1.getName());
        prodDTO.setPrice(prod1.getPrice());
        prodDTO.setStatus(Status.Deactive);
        prodDTO.setQuantity(prod1.getQuantity());

        when(productRepository.findById(id)).thenReturn(Optional.of(prod1));
        when(productRepository.save(prod1)).thenReturn(prod1);
        when(productMapper.toProductDTO(prod1)).thenReturn(prodDTO);

        ProductDTO result = productService.updateProductStatus(id, Status.Deactive);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(prod1.getId(), result.getId());
        Assertions.assertEquals(Status.Deactive, result.getStatus());
    }

    @Test
    void productServiceImpl_updateProductStatus_returnStatusDeactiveChangeProductDTO() {
        UUID id = prod1.getId();
        prod1.setStatus(Status.Deactive);

        ProductDTO prodDTO = new ProductDTO();
        prodDTO.setId(prod1.getId());
        prodDTO.setName(prod1.getName());
        prodDTO.setPrice(prod1.getPrice());
        prodDTO.setStatus(Status.Active);
        prodDTO.setQuantity(prod1.getQuantity());

        when(productRepository.findById(id)).thenReturn(Optional.of(prod1));
        when(productRepository.save(prod1)).thenReturn(prod1);
        when(productMapper.toProductDTO(prod1)).thenReturn(prodDTO);

        ProductDTO result = productService.updateProductStatus(id, Status.Active);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(prod1.getId(), result.getId());
        Assertions.assertEquals(Status.Active, result.getStatus());
    }

    @Test
    void productServiceImpl_updateProductStatus_returnDuplicateStatus() {
        UUID id = prod1.getId();

        when(productRepository.findById(id)).thenReturn(Optional.of(prod1));

        Assertions.assertThrows(DuplicateStatusException.class, () -> productService.updateProductStatus(id, Status.Active));
    }

    @Test
    void productServiceImpl_saveProductsFromCSV_returnListProductDTO() throws Exception {
        ProductSaveDTO prodSaveDTO = new ProductSaveDTO();
        prodSaveDTO.setName("Bag");
        prodSaveDTO.setPrice(120.89);
        prodSaveDTO.setQuantity(10);

        ProductDTO prodDTO = new ProductDTO();
        prodDTO.setId(UUID.randomUUID());
        prodDTO.setName("Bag");
        prodDTO.setPrice(120.89);
        prodDTO.setQuantity(10);
        prodDTO.setStatus(Status.Active);

        List<ProductSaveDTO> productSaveDTOList = Arrays.asList(prodSaveDTO);
        List<Product> productList = Arrays.asList(prod1);
        List<ProductDTO> productDTOList = Arrays.asList(prodDTO);
        
        MultipartFile file = new MockMultipartFile("file", "products.csv", "text/csv", "name,price,quantity\nBag,120.89,10".getBytes());

        try (MockedStatic<FileUtils> mockedStatic = mockStatic(FileUtils.class)) {
            mockedStatic.when(() -> FileUtils.hasCSVFormat(file)).thenReturn(true);
            mockedStatic.when(() -> FileUtils.readProductsFromCSV(file)).thenReturn(productSaveDTOList);

            when(productMapper.toProductList(productSaveDTOList)).thenReturn(productList);
            when(productRepository.saveAll(productList)).thenReturn(productList);
            when(productMapper.toProductDTOList(productList)).thenReturn(productDTOList);

            List<ProductDTO> result = productService.saveProductsFromCSV(file);

            Assertions.assertNotNull(result);
            Assertions.assertEquals(1, result.size());
            Assertions.assertEquals("Bag", result.get(0).getName());
            Assertions.assertEquals(120.89, result.get(0).getPrice());
            Assertions.assertEquals(10, result.get(0).getQuantity());
        }
    }

    @Test
    void productServiceImpl_saveProductsFromCSV_returnInvalidFileFormat() throws Exception {
        MultipartFile file = new MockMultipartFile("file", "products.csv", "text/csv", "name,price,quantity\nBag,120.89,10".getBytes());

        try(MockedStatic<FileUtils> mockedStatic = mockStatic(FileUtils.class)) {
            mockedStatic.when(() -> FileUtils.hasCSVFormat(file)).thenReturn(false);

            Assertions.assertThrows(IllegalArgumentException.class, () -> productService.saveProductsFromCSV(file));
        }
    }

    @Test
    void productServiceImpl_saveProductsFromCSV_returnIoException() throws Exception {
        MultipartFile file = new MockMultipartFile("file", "products.csv", "text/csv", "name,price,quantity\nBag,120.89,10".getBytes());

        try(MockedStatic<FileUtils> mockedStatic = mockStatic(FileUtils.class)) {
            mockedStatic.when(() -> FileUtils.hasCSVFormat(file)).thenReturn(true);
            mockedStatic.when(() -> FileUtils.readProductsFromCSV(file)).thenThrow(new IOException("File read error"));

            Assertions.assertThrows(BadRequestException.class, () -> productService.saveProductsFromCSV(file));
        }
    }

    @Test
    void productServiceImpl_saveProductsFromCSV_returnStatusDeactiveProducts() throws Exception {
        ProductSaveDTO prodSaveDTO = new ProductSaveDTO();
        prodSaveDTO.setName("Bag");
        prodSaveDTO.setPrice(120.89);
        prodSaveDTO.setQuantity(0);

        ProductDTO prodDTO = new ProductDTO();
        prodDTO.setId(UUID.randomUUID());
        prodDTO.setName("Bag");
        prodDTO.setPrice(120.89);
        prodDTO.setQuantity(0);
        prodDTO.setStatus(Status.Deactive);

        List<ProductSaveDTO> productSaveDTOList = Arrays.asList(prodSaveDTO);
        List<Product> productList = Arrays.asList(mock(Product.class));
        List<ProductDTO> productDTOList = Arrays.asList(prodDTO);
        
        Product product = productList.get(0);
        when(product.getQuantity()).thenReturn(0);
        when(product.getStatus()).thenReturn(Status.Deactive);

        MultipartFile file = new MockMultipartFile("file", "products.csv", "text/csv", "name,price,quantity\nBag,120.89,0".getBytes());

        try (MockedStatic<FileUtils> mockedStatic = mockStatic(FileUtils.class)) {
            mockedStatic.when(() -> FileUtils.hasCSVFormat(file)).thenReturn(true);
            mockedStatic.when(() -> FileUtils.readProductsFromCSV(file)).thenReturn(productSaveDTOList);

            when(productMapper.toProductList(productSaveDTOList)).thenReturn(productList);
            when(productRepository.saveAll(productList)).thenReturn(productList);
            when(productMapper.toProductDTOList(productList)).thenReturn(productDTOList);

            List<ProductDTO> result = productService.saveProductsFromCSV(file);

            Assertions.assertNotNull(result);
            Assertions.assertEquals(1, result.size());
            Assertions.assertEquals(0, result.get(0).getQuantity());
            Assertions.assertEquals(Status.Deactive, result.get(0).getStatus());
        }
    }
}
