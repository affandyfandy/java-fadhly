package com.example.fpt_midterm_pos.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.fpt_midterm_pos.data.model.Status;
import com.example.fpt_midterm_pos.data.model.Customer;
import com.example.fpt_midterm_pos.dto.CustomerInvoiceDTO;
import com.example.fpt_midterm_pos.dto.InvoiceDetailDTO;
import com.example.fpt_midterm_pos.dto.InvoiceDetailSaveDTO;
import com.example.fpt_midterm_pos.dto.InvoiceDTO;
import com.example.fpt_midterm_pos.dto.InvoiceSaveDTO;
import com.example.fpt_midterm_pos.dto.InvoiceSearchCriteriaDTO;
import com.example.fpt_midterm_pos.dto.ProductDTO;
import com.example.fpt_midterm_pos.dto.InvoiceDetailsSearchCriteriaDTO;
import com.example.fpt_midterm_pos.dto.RevenueShowDTO;
import com.example.fpt_midterm_pos.service.CustomerService;
import com.example.fpt_midterm_pos.service.InvoiceService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.text.SimpleDateFormat;
import java.util.*;

@WebMvcTest(InvoiceController.class)
public class InvoiceControllerTest {

    @MockBean
    private InvoiceService invoiceService;

    @MockBean
    private CustomerService customerService;

    @InjectMocks
    private InvoiceController invoiceController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    private CustomerInvoiceDTO customerInvoiceDTO;
    private ProductDTO productDTO;
    private InvoiceDetailDTO invoiceDetailDTO;
    private InvoiceDetailSaveDTO invoiceDetailSaveDTO;

    private InvoiceDTO invoiceDTO;
    private InvoiceSaveDTO invoiceSaveDTO;
    private InvoiceSearchCriteriaDTO criteria;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(invoiceController).build();
        objectMapper = new ObjectMapper();

        customerInvoiceDTO = new CustomerInvoiceDTO(UUID.randomUUID(), "Alex Brown");
        productDTO = new ProductDTO(UUID.randomUUID(), "Laptop", 159.99, Status.Active, 10);
        invoiceDetailDTO = new InvoiceDetailDTO(productDTO.getId(), productDTO.getName(), 1, 159.99, 159.99);
        invoiceDetailSaveDTO = new InvoiceDetailSaveDTO(productDTO.getId(), 1);

        invoiceDTO = new InvoiceDTO(UUID.randomUUID(), 159.99, new Date(), customerInvoiceDTO, List.of(invoiceDetailDTO));
        invoiceSaveDTO = new InvoiceSaveDTO(customerInvoiceDTO.getId(), List.of(invoiceDetailSaveDTO));
        criteria = new InvoiceSearchCriteriaDTO();
    }

    @Test
    void invoiceController_getInvoice_returnAllInvoices() throws Exception {
        Pageable pageable = PageRequest.of(0, 20);
        List<InvoiceDTO> invoiceDTOs = List.of(invoiceDTO);

        Page<InvoiceDTO> invoicePage = new PageImpl<>(invoiceDTOs, pageable, invoiceDTOs.size());

        when(invoiceService.findByCriteria(criteria, pageable)).thenReturn(invoicePage);

        String expectedResult = objectMapper.writeValueAsString(invoicePage);

        mockMvc.perform(get("/api/v1/invoices")
                .param("page", "0")
                .param("size", "20"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isNotEmpty())
                .andExpect(content().json(expectedResult));
    }

    @Test
    void invoiceController_getInvoice_returnNoContent() throws Exception {
        Pageable pageable = PageRequest.of(0, 20);
        List<InvoiceDTO> invoiceDTOs = List.of();

        Page<InvoiceDTO> invoicePage = new PageImpl<>(invoiceDTOs, pageable, invoiceDTOs.size());

        when(invoiceService.findByCriteria(criteria, pageable)).thenReturn(invoicePage);

        mockMvc.perform(get("/api/v1/invoices")
                .param("page", "0")
                .param("size", "20"))
                .andExpect(status().isNoContent());
    }

    @Test
    void invoiceController_createInvoice_returnInvoiceDTO() throws Exception {
        when(invoiceService.createInvoice(invoiceSaveDTO)).thenReturn(invoiceDTO);

        String expectedInput = objectMapper.writeValueAsString(invoiceSaveDTO);
        String expectedResult = objectMapper.writeValueAsString(invoiceDTO);

        mockMvc.perform(post("/api/v1/invoices")
                .contentType(MediaType.APPLICATION_JSON)
                .content(expectedInput))
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedResult))
                .andExpect(jsonPath("$.id").value(invoiceDTO.getId().toString()));
    }

    @Test
    void invoiceController_updateInvoice_returnInvoiceDTO() throws Exception {
        UUID invoiceId = invoiceDTO.getId();

        when(invoiceService.updateInvoice(invoiceId, invoiceSaveDTO)).thenReturn(invoiceDTO);

        String expectedInput = objectMapper.writeValueAsString(invoiceSaveDTO);
        String expectedResult = objectMapper.writeValueAsString(invoiceDTO);

        mockMvc.perform(put("/api/v1/invoices/{id}", invoiceId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(expectedInput))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResult))
                .andExpect(jsonPath("$.id").value(invoiceDTO.getId().toString()));
    }

    @Test
    void invoiceController_exportInvoiceToPDF_returnPDF() throws Exception {
        UUID invoiceId = invoiceDTO.getId();
        byte[] pdfBytes = new byte[]{1, 2, 3};

        when(invoiceService.exportInvoiceToPDF(invoiceId)).thenReturn(pdfBytes);

        mockMvc.perform(get("/api/v1/invoices/{id}/export", invoiceId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_PDF))
                .andExpect(header().string("Content-Disposition", "attachment; filename=\"invoice_" + invoiceId + ".pdf\""))
                .andExpect(content().bytes(pdfBytes));
    }

    @Test
    void invoiceController_exportInvoiceToExcel_returnExcel() throws Exception {
        Workbook workbook = mock(Workbook.class);

        Customer cust = new Customer();
        cust.setId(UUID.randomUUID());
        cust.setName("Bjorn Kiv");

        InvoiceDetailsSearchCriteriaDTO criteria2 = new InvoiceDetailsSearchCriteriaDTO(cust.getId(), 8, 2024);

        when(customerService.findById(cust.getId())).thenReturn(cust);
        when(invoiceService.exportInvoiceToExcelByFilter(criteria2)).thenReturn(workbook);

        mockMvc.perform(get("/api/v1/invoices/excel")
                .param("customerId", cust.getId().toString())
                .param("month", "8")
                .param("year", "2024"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Disposition", "attachment; filename=invoice_report_Bjorn_Kiv_8_2024.xlsx"))
                .andExpect(content().contentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
    }

    @Test
    void invoiceController_getRevenue_returnRevenueShowDTO() throws Exception {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String formattedDate = dateFormat.format(date);

        RevenueShowDTO revenueShowDTO = new RevenueShowDTO();

        when(invoiceService.getInvoicesRevenue(date, "month")).thenReturn(revenueShowDTO);

        mockMvc.perform(get("/api/v1/invoices/revenue")
                .param("date", formattedDate)
                .param("revenueBy", "month"))
                .andExpect(status().isOk());
    }
}
