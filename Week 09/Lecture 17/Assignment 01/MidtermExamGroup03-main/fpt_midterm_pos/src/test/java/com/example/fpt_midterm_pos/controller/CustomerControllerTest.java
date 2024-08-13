package com.example.fpt_midterm_pos.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.fpt_midterm_pos.data.model.Status;
import com.example.fpt_midterm_pos.dto.CustomerDTO;
import com.example.fpt_midterm_pos.dto.CustomerShowDTO;
import com.example.fpt_midterm_pos.dto.CustomerSaveDTO;
import com.example.fpt_midterm_pos.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.*;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @MockBean
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    private CustomerDTO customerDTO;
    private CustomerDTO customerDTO2;
    private CustomerSaveDTO customerSaveDTO;
    private CustomerShowDTO customerShowDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
        objectMapper = new ObjectMapper();

        customerDTO = new CustomerDTO(UUID.randomUUID(), "Alice Rock", "+628463517283", Status.Active);
        customerDTO2 = new CustomerDTO(UUID.randomUUID(), "Alice Rock", "+628463517283", Status.Deactive);
        customerSaveDTO = new CustomerSaveDTO("Alice Rock", "+628463517283");
        customerShowDTO = new CustomerShowDTO(UUID.randomUUID(), "Alice Rock", "+628463517283");
    }

    @Test
    void customerController_getAllCustomer_returnAllCustomers() throws Exception {
        Pageable pageable = PageRequest.of(0, 20);
        List<CustomerShowDTO> customerShowDTOs = List.of(customerShowDTO);

        Page<CustomerShowDTO> customerPage = new PageImpl<>(customerShowDTOs, pageable, customerShowDTOs.size());
        when(customerService.findAllActiveCustomer(pageable)).thenReturn(customerPage);

        String expectedResult = objectMapper.writeValueAsString(customerPage);

        mockMvc.perform(get("/api/v1/customers")
                .param("page", "0")
                .param("size", "20"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isNotEmpty())
                .andExpect(content().json(expectedResult));
    }

    @Test
    void customerController_getAllCustomer_returnNoContent() throws Exception {
        Pageable pageable = PageRequest.of(0, 20);
        List<CustomerShowDTO> customerShowDTOs = List.of();

        Page<CustomerShowDTO> customerPage = new PageImpl<>(customerShowDTOs, pageable, customerShowDTOs.size());
        when(customerService.findAllActiveCustomer(pageable)).thenReturn(customerPage);

        mockMvc.perform(get("/api/v1/customers")
                .param("page", "0")
                .param("size", "20"))
                .andExpect(status().isNoContent());
    }

    @Test
    void customerController_createCustomer_returnCustomerDTO() throws Exception {
        when(customerService.createCustomer(customerSaveDTO)).thenReturn(customerDTO);

        String expectedInput = objectMapper.writeValueAsString(customerSaveDTO);
        String expectedResult = objectMapper.writeValueAsString(customerDTO);

        mockMvc.perform(post("/api/v1/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(expectedInput))
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedResult))
                .andExpect(jsonPath("$.id").value(customerDTO.getId().toString()));
    }

    @Test
    void customerController_updateCustomer_returnCustomerDTO() throws Exception {
        UUID customerId = customerDTO.getId();
        customerDTO.setName("Olivia Rock");
        customerDTO.setPhoneNumber("+6283465729182");

        when(customerService.updateCustomer(customerId, customerSaveDTO)).thenReturn(customerDTO);

        String expectedInput = objectMapper.writeValueAsString(customerSaveDTO);
        String expectedResult = objectMapper.writeValueAsString(customerDTO);

        mockMvc.perform(put("/api/v1/customers/{id}", customerId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(expectedInput))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResult))
                .andExpect(jsonPath("$.id").value(customerDTO.getId().toString()))
                .andExpect(jsonPath("$.name").value(customerDTO.getName().toString()))
                .andExpect(jsonPath("$.phoneNumber").value(customerDTO.getPhoneNumber().toString()));
    }

    @Test
    void customerController_updateCustomerStatusActive_returnCustomerDTO() throws Exception {
        UUID customerId = customerDTO2.getId();
        customerDTO2.setStatus(Status.Active);

        when(customerService.updateCustomerStatus(customerId, Status.Active)).thenReturn(customerDTO2);
        
        String expectedResult = objectMapper.writeValueAsString(customerDTO2);

        mockMvc.perform(put("/api/v1/customers/active/{id}", customerId))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResult))
                .andExpect(jsonPath("$.id").value(customerDTO2.getId().toString()))
                .andExpect(jsonPath("$.status").value(customerDTO2.getStatus().toString()));
    }

    @Test
    void customerController_updateCustomerStatusDeactive_returnCustomerDTO() throws Exception {
        UUID customerId = customerDTO.getId();
        customerDTO.setStatus(Status.Deactive);

        when(customerService.updateCustomerStatus(customerId, Status.Deactive)).thenReturn(customerDTO);
        
        String expectedResult = objectMapper.writeValueAsString(customerDTO);

        mockMvc.perform(put("/api/v1/customers/deactive/{id}", customerId))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResult))
                .andExpect(jsonPath("$.id").value(customerDTO.getId().toString()))
                .andExpect(jsonPath("$.status").value(customerDTO.getStatus().toString()));
    }
}
