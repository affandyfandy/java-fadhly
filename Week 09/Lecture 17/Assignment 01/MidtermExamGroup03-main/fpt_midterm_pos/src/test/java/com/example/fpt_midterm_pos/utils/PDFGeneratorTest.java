package com.example.fpt_midterm_pos.utils;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.example.fpt_midterm_pos.data.model.Invoice;
import com.example.fpt_midterm_pos.data.model.Customer;
import com.example.fpt_midterm_pos.data.model.InvoiceDetail;

import java.util.*;

public class PDFGeneratorTest {

    @Mock
    private SpringTemplateEngine templateEngine;

    @InjectMocks
    private PDFGenerator pdfGenerator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGenerateInvoicePDF_Success() throws IOException {
        Invoice invoice = mock(Invoice.class);
        Customer customer = mock(Customer.class);
        InvoiceDetail detail1 = new InvoiceDetail();
        detail1.setQuantity(2);
        detail1.setPrice(10.0);
        InvoiceDetail detail2 = new InvoiceDetail();
        detail2.setQuantity(3);
        detail2.setPrice(15.0);

        when(invoice.getCustomer()).thenReturn(customer);
        when(invoice.getInvoiceDetails()).thenReturn(Arrays.asList(detail1, detail2));

        String expectedHtml = "<html>Mocked HTML Content</html>";
        when(templateEngine.process(eq("invoice-template"), any(Context.class))).thenReturn(expectedHtml);

        byte[] pdfBytes = pdfGenerator.generateInvoicePDF(invoice);

        assertNotNull(pdfBytes);
        assertTrue(pdfBytes.length > 0);
    }

    @Test
    void testGenerateInvoicePDF_RuntimeException() {
        Invoice invoice = mock(Invoice.class);
        when(templateEngine.process(eq("invoice-template"), any(Context.class))).thenThrow(new RuntimeException("PDF generation failed"));

        assertThrows(RuntimeException.class, () -> pdfGenerator.generateInvoicePDF(invoice));
    }

    @Test
    void testGenerateInvoicePDF_NullInvoice() {
        assertThrows(NullPointerException.class, () -> pdfGenerator.generateInvoicePDF(null));
    }
}
