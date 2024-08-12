package com.example.fpt_midterm_pos.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.ContextConfiguration;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.fpt_midterm_pos.data.model.Customer;
import com.example.fpt_midterm_pos.data.model.Invoice;
import com.example.fpt_midterm_pos.data.model.InvoiceDetail;
import com.example.fpt_midterm_pos.data.model.InvoiceDetailKey;
import com.example.fpt_midterm_pos.data.model.Product;
import com.example.fpt_midterm_pos.data.model.Status;
import com.example.fpt_midterm_pos.dto.InvoiceDTO;
import com.example.fpt_midterm_pos.dto.InvoiceDetailSaveDTO;
import com.example.fpt_midterm_pos.dto.InvoiceDetailsSearchCriteriaDTO;
import com.example.fpt_midterm_pos.dto.InvoiceSaveDTO;
import com.example.fpt_midterm_pos.dto.InvoiceSearchCriteriaDTO;
import com.example.fpt_midterm_pos.dto.RevenueShowDTO;
import com.example.fpt_midterm_pos.mapper.InvoiceMapper;
import com.example.fpt_midterm_pos.exception.BadRequestException;
import com.example.fpt_midterm_pos.exception.DuplicateStatusException;
import com.example.fpt_midterm_pos.data.repository.CustomerRepository;
import com.example.fpt_midterm_pos.data.repository.InvoiceDetailRepository;
import com.example.fpt_midterm_pos.data.repository.InvoiceRepository;
import com.example.fpt_midterm_pos.data.repository.ProductRepository;
import com.example.fpt_midterm_pos.service.impl.InvoiceServiceImpl;
import com.example.fpt_midterm_pos.utils.ExcelGenerator;
import com.example.fpt_midterm_pos.utils.PDFGenerator;
import com.example.fpt_midterm_pos.utils.DateUtils;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class InvoiceServiceImplTest {

    @InjectMocks
    InvoiceServiceImpl invoiceService;

    @Mock
    private InvoiceRepository invoiceRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private InvoiceDetailRepository invoiceDetailRepository;

    @Mock
    private InvoiceMapper invoiceMapper;

    @Mock
    private PDFGenerator pdfGenerator;

    private Invoice invc;
    private Customer cust;
    private Product prod;
    private InvoiceDetail invcDet;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);

        cust = new Customer();
        cust.setId(UUID.randomUUID());

        prod = new Product();
        prod.setId(UUID.randomUUID());
        prod.setName("Product A");
        prod.setPrice(100.0);
        prod.setQuantity(50);
        prod.setStatus(Status.Active);

        invc = new Invoice();
        invc.setId(UUID.randomUUID());
        invc.setCustomer(cust);
        invc.setAmount(0.0);
        invc.setDate(new Date());
        invc.setCreatedAt(new Date());
        invc.setUpdatedAt(new Date());

        invcDet = new InvoiceDetail();
        invcDet.setId(new InvoiceDetailKey(invc.getId(), prod.getId()));
        invcDet.setProduct(prod);
        invcDet.setQuantity(10);
        invcDet.setAmount(1000.0);
    }

    @Test
    void invoiceServiceImpl_findByCriteria_returnPagesInvoiceDTO() {
        InvoiceSearchCriteriaDTO criteria = new InvoiceSearchCriteriaDTO();
        criteria.setCustomerName("test");
        criteria.setCustomerId(cust.getId());
        criteria.setStartDate(new Date());
        criteria.setEndDate(new Date());
        criteria.setMonth(8);
        criteria.setSortByDate("asc");
        criteria.setSortByAmount("asc");

        Pageable pageable = PageRequest.of(0, 20);
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        Invoice invoice = new Invoice();

        List<Invoice> invoiceList = List.of(invoice);
        Page<Invoice> invoicePage = new PageImpl<>(invoiceList, pageable, invoiceList.size());

        when(invoiceRepository.findByFilters(anyString(), any(), any(), any(), anyInt(), anyString(), anyString(), any(Pageable.class))).thenReturn(invoicePage);
        when(invoiceMapper.toInvoiceDTO(invoice)).thenReturn(invoiceDTO);

        Page<InvoiceDTO> result = invoiceService.findByCriteria(criteria, pageable);

        Assertions.assertNotNull(result);
    }

    @Test
    void invoiceServiceImpl_createInvoice_returnInvoiceDTO() {
        InvoiceSaveDTO invoiceSaveDTO = new InvoiceSaveDTO();
        invoiceSaveDTO.setCustomerId(UUID.randomUUID());
        invoiceSaveDTO.setInvoiceDetails(List.of(new InvoiceDetailSaveDTO(UUID.randomUUID(), 2)));

        Customer customer = new Customer();
        when(customerRepository.findById(invoiceSaveDTO.getCustomerId())).thenReturn(Optional.of(customer));

        Product product = new Product();
        product.setStatus(Status.Active);
        product.setQuantity(5);
        product.setPrice(100.0);
        when(productRepository.findById(any(UUID.class))).thenReturn(Optional.of(product));

        when(invoiceRepository.save(any(Invoice.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(invoiceMapper.toInvoiceDTO(any(Invoice.class))).thenReturn(new InvoiceDTO());

        InvoiceDTO result = invoiceService.createInvoice(invoiceSaveDTO);

        Assertions.assertNotNull(result);
    }

    @Test
    void invoiceServiceImpl_updateInvoice_returnInvoiceDTO() {
        UUID invoiceId = UUID.randomUUID();
        UUID productId = UUID.randomUUID();

        InvoiceSaveDTO invoiceSaveDTO = new InvoiceSaveDTO();
        invoiceSaveDTO.setInvoiceDetails(List.of(new InvoiceDetailSaveDTO(productId, 2)));

        Invoice existingInvoice = new Invoice();
        existingInvoice.setCreatedAt(new Date());

        Product product = new Product();
        product.setId(productId);
        product.setStatus(Status.Active);
        product.setQuantity(5);
        product.setPrice(100.0);

        InvoiceDetail invoiceDetail = new InvoiceDetail();
        invoiceDetail.setProduct(product);
        invoiceDetail.setQuantity(2);
    
        List<InvoiceDetail> mockInvoiceDetails = List.of(invoiceDetail);
        existingInvoice.setInvoiceDetails(mockInvoiceDetails);

        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(existingInvoice));

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        when(invoiceRepository.save(any(Invoice.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(invoiceMapper.toInvoiceDTO(any(Invoice.class))).thenReturn(new InvoiceDTO());

        InvoiceDTO result = invoiceService.updateInvoice(invoiceId, invoiceSaveDTO);

        Assertions.assertNotNull(result);
    }

    @Test
    void invoiceServiceImpl_exportInvoiceToPDF_returnPdf() throws IOException {
        UUID invoiceId = UUID.randomUUID();
        Invoice invoice = new Invoice();
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(invoice));
        byte[] pdfData = new byte[]{1, 2, 3};
        when(pdfGenerator.generateInvoicePDF(invoice)).thenReturn(pdfData);

        byte[] result = invoiceService.exportInvoiceToPDF(invoiceId);

        Assertions.assertArrayEquals(pdfData, result);
    }

    @Test
    void InvoiceServiceImpl_exportInvoiceToExcelByFilter_returnWorkbook() {
        InvoiceDetailsSearchCriteriaDTO criteria = new InvoiceDetailsSearchCriteriaDTO();
        criteria.setCustomerId(UUID.randomUUID());
        criteria.setMonth(8);
        criteria.setYear(2024);

        Invoice invoice = new Invoice();
        invoice.setInvoiceDetails(new ArrayList<>()); // Ensure invoiceDetails is not null

        // Prepare the list of invoices
        List<Invoice> invoices = List.of(invoice);

        // Mock repository behavior
        when(invoiceRepository.findByFiltersForExcel(any(UUID.class), anyInt(), anyInt())).thenReturn(invoices);

        // Use MockedStatic to mock static method
        try (MockedStatic<ExcelGenerator> mockedStatic = mockStatic(ExcelGenerator.class)) {
            Workbook workbook = mock(Workbook.class);
            mockedStatic.when(() -> ExcelGenerator.generateInvoiceExcel(invoices)).thenReturn(workbook);

            // Call the method under test
            Workbook result = invoiceService.exportInvoiceToExcelByFilter(criteria);

            Assertions.assertNotNull(result);
        }
    }

    @Test
    void invoiceServiceImpl_getInvoicesRevenue_returnRevenueShowDTO() {
        try (MockedStatic<DateUtils> mockedStatic = mockStatic(DateUtils.class)) {
            Date date = new Date();
            LocalDate localDate = LocalDate.now();

            // Mocking DateUtils.formatDateToLocalDate to return LocalDate.now()
            mockedStatic.when(() -> DateUtils.formatDateToLocalDate(date)).thenReturn(localDate);

            // Mocking repository methods
            when(invoiceRepository.findTotalRevenueByYear(anyInt())).thenReturn(1000.0);
            when(invoiceRepository.findTotalRevenueByMonth(anyInt(), anyInt())).thenReturn(500.0);
            when(invoiceRepository.findTotalRevenueByDay(any(Date.class))).thenReturn(100.0);

            // Test cases for different periods
            RevenueShowDTO result = invoiceService.getInvoicesRevenue(date, "year");
            assertEquals(1000.0, result.getAmount());

            result = invoiceService.getInvoicesRevenue(date, "month");
            assertEquals(500.0, result.getAmount());

            result = invoiceService.getInvoicesRevenue(date, "day");
            assertEquals(100.0, result.getAmount());

            // Test case for invalid period
            assertThrows(IllegalArgumentException.class, () -> invoiceService.getInvoicesRevenue(date, "invalid"));
        }
    }
}