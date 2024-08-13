package com.example.fpt_midterm_pos.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.example.fpt_midterm_pos.dto.ProductSaveDTO;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import java.util.*;

public class FileUtilsTest {
    
    @Test
    void testHasCSVFormat_ValidCSV() {
        MultipartFile file = new MockMultipartFile(
                "file",
                "products.csv",
                "text/csv",
                "name,price,quantity\nProduct1,10.0,5".getBytes()
        );
        assertTrue(FileUtils.hasCSVFormat(file));
    }

    @Test
    void testHasCSVFormat_InvalidFormat() {
        MultipartFile file = new MockMultipartFile(
                "file",
                "products.txt",
                "text/plain",
                "name,price,quantity\nProduct1,10.0,5".getBytes()
        );
        assertFalse(FileUtils.hasCSVFormat(file));
    }

    @Test
    void testReadProductsFromCSV_ValidCSV() throws IOException {
        String csvData = "name,price,quantity\nProduct1,10.0,5\nProduct2,20.0,10";
        MultipartFile file = new MockMultipartFile(
                "file",
                "products.csv",
                "text/csv",
                csvData.getBytes(StandardCharsets.UTF_8)
        );

        List<ProductSaveDTO> products = FileUtils.readProductsFromCSV(file);
        assertNotNull(products);
        assertEquals(2, products.size());
        assertEquals("Product1", products.get(0).getName());
        assertEquals(10.0, products.get(0).getPrice());
        assertEquals(5, products.get(0).getQuantity());
    }

    @Test
    void testReadProductsFromCSV_InvalidCSV() {
        String invalidCsvData = "name,price,quantity\nProduct1,10.0"; // Missing quantity
        MultipartFile file = new MockMultipartFile(
                "file",
                "products.csv",
                "text/csv",
                invalidCsvData.getBytes(StandardCharsets.UTF_8)
        );

        assertThrows(IllegalArgumentException.class, () -> {
            FileUtils.readProductsFromCSV(file);
        });
    }

    @Test
    void testFromCSV_ValidAttributes() {
        String[] attributes = {"Product1", "10.0", "5"};
        ProductSaveDTO productSaveDTO = FileUtils.fromCSV(attributes);

        assertNotNull(productSaveDTO);
        assertEquals("Product1", productSaveDTO.getName());
        assertEquals(10.0, productSaveDTO.getPrice());
        assertEquals(5, productSaveDTO.getQuantity());
    }

    @Test
    void testFromCSV_InvalidAttributes() {
        String[] invalidAttributes = {"Product1", "10.0"}; // Missing quantity
        assertThrows(IllegalArgumentException.class, () -> FileUtils.fromCSV(invalidAttributes));
    }

    @Test
    void testReadProductsFromCSV_IOException() throws IOException {
        MultipartFile file = Mockito.mock(MultipartFile.class);
        Mockito.when(file.getInputStream()).thenThrow(new IOException("Test exception"));

        IOException exception = assertThrows(IOException.class, () -> {
            FileUtils.readProductsFromCSV(file);
        });

        assertEquals("Error reading CSV file: Test exception", exception.getMessage());
    }
}
