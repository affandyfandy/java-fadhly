package com.example.fpt_midterm_pos.utils;

import com.example.fpt_midterm_pos.data.model.Invoice;
import com.example.fpt_midterm_pos.data.model.InvoiceDetail;
import com.example.fpt_midterm_pos.data.model.Customer;
import com.example.fpt_midterm_pos.data.model.Product;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class ExcelGeneratorTest {
    
    @Test
    void excelGenerator_testGenerateInvoiceExcel() {

        Customer customer = new Customer();
        customer.setId(UUID.randomUUID());
        customer.setName("Alice Key");

        Product product = new Product();
        product.setId(UUID.randomUUID());
        product.setName("Laptop");

        InvoiceDetail detail = new InvoiceDetail();
        detail.setProduct(product);
        detail.setPrice(50.0);
        detail.setQuantity(2);
        detail.setAmount(100.0);

        Invoice invoice = new Invoice();
        invoice.setId(UUID.randomUUID());
        invoice.setCustomer(customer);
        invoice.setAmount(100.0);
        invoice.setInvoiceDetails(List.of(detail));

        List<Invoice> invoices = List.of(invoice);

        Workbook workbook = ExcelGenerator.generateInvoiceExcel(invoices);

        Sheet sheet = workbook.getSheet("Invoices");
        Assertions.assertNotNull(sheet, "Sheet 'Invoices' should exist");

        Row headerRow = sheet.getRow(0);
        Assertions.assertEquals("Invoice ID", headerRow.getCell(0).getStringCellValue());
        Assertions.assertEquals("Customer ID", headerRow.getCell(1).getStringCellValue());
        Assertions.assertEquals("Customer Name", headerRow.getCell(2).getStringCellValue());
        Assertions.assertEquals("Amount", headerRow.getCell(3).getStringCellValue());
        Assertions.assertEquals("Product ID", headerRow.getCell(4).getStringCellValue());
        Assertions.assertEquals("Product Name", headerRow.getCell(5).getStringCellValue());
        Assertions.assertEquals("Price", headerRow.getCell(6).getStringCellValue());
        Assertions.assertEquals("Quantity", headerRow.getCell(7).getStringCellValue());
        Assertions.assertEquals("Product Amount", headerRow.getCell(8).getStringCellValue());

        Row dataRow = sheet.getRow(1);
        Assertions.assertEquals(invoice.getId().toString(), dataRow.getCell(0).getStringCellValue());  // Invoice ID
        Assertions.assertEquals(customer.getId().toString(), dataRow.getCell(1).getStringCellValue());  // Customer ID
        Assertions.assertEquals("Alice Key", dataRow.getCell(2).getStringCellValue());  // Customer Name
        Assertions.assertEquals(100.0, dataRow.getCell(3).getNumericCellValue());  // Amount
        Assertions.assertEquals(product.getId().toString(), dataRow.getCell(4).getStringCellValue());  // Product ID
        Assertions.assertEquals("Laptop", dataRow.getCell(5).getStringCellValue());  // Product Name
        Assertions.assertEquals(50.0, dataRow.getCell(6).getNumericCellValue());  // Price
        Assertions.assertEquals(2, dataRow.getCell(7).getNumericCellValue());  // Quantity
        Assertions.assertEquals(100.0, dataRow.getCell(8).getNumericCellValue());  // Product Amount

        Assertions.assertEquals(15 * 256, sheet.getColumnWidth(0));  // Invoice ID
        Assertions.assertEquals(15 * 256, sheet.getColumnWidth(1));  // Customer ID
        Assertions.assertEquals(15 * 256, sheet.getColumnWidth(2));  // Customer Name
        Assertions.assertEquals(15 * 256, sheet.getColumnWidth(3));  // Amount
        Assertions.assertEquals(15 * 256, sheet.getColumnWidth(4));  // Product ID
        Assertions.assertEquals(15 * 256, sheet.getColumnWidth(5));  // Product Name
        Assertions.assertEquals(15 * 256, sheet.getColumnWidth(6));  // Price
        Assertions.assertEquals(15 * 256, sheet.getColumnWidth(7));  // Quantity
        Assertions.assertEquals(15 * 256, sheet.getColumnWidth(8));  // Product Amount
    }
}
