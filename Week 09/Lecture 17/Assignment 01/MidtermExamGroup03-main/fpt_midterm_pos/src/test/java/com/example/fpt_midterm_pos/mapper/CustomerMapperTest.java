package com.example.fpt_midterm_pos.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.fpt_midterm_pos.data.model.Customer;
import com.example.fpt_midterm_pos.data.model.Status;
import com.example.fpt_midterm_pos.mapper.CustomerMapper;
import com.example.fpt_midterm_pos.dto.CustomerDTO;
import com.example.fpt_midterm_pos.dto.CustomerInvoiceDTO;
import com.example.fpt_midterm_pos.dto.CustomerShowDTO;
import com.example.fpt_midterm_pos.dto.CustomerSaveDTO;

import org.mapstruct.factory.Mappers;

import java.util.*;

public class CustomerMapperTest {
    
    private CustomerMapper customerMapper;

    private Customer customer;
    private CustomerDTO customerDTO;
    private CustomerSaveDTO customerSaveDTO;
    private CustomerShowDTO customerShowDTO;
    private CustomerInvoiceDTO customerInvoiceDTO;

    @BeforeEach
    void init() {
        customerMapper = Mappers.getMapper(CustomerMapper.class);

        customer = new Customer(UUID.randomUUID(), "Alice Smith", "+62837194857362", Status.Active, new Date(), new Date(), null);
        customerDTO = new CustomerDTO(UUID.randomUUID(), "Alice Smith", "+62837194857362", Status.Deactive);
        customerSaveDTO = new CustomerSaveDTO("Alice Smith", "+62837194857362");
        customerShowDTO = new CustomerShowDTO(UUID.randomUUID(), "Alice Smith", "+62837194857362");
        customerInvoiceDTO = new CustomerInvoiceDTO(UUID.randomUUID(), "Alice Smith");
    }

    @Test
    void customerMapper_toCustomerDTO_returnCustomerDTO() {
        CustomerDTO custDTO = customerMapper.toCustomerDTO(customer);

        Assertions.assertEquals(customer.getId(), custDTO.getId());
        Assertions.assertEquals(customer.getName(), custDTO.getName());
        Assertions.assertEquals(customer.getPhoneNumber(), custDTO.getPhoneNumber());
        Assertions.assertEquals(customer.getStatus(), custDTO.getStatus());
    }

    @Test
    void customerMapper_toCustomerFromCustomerDTO_returnCustomer() {
        Customer cust = customerMapper.toCustomer(customerDTO);

        Assertions.assertEquals(customerDTO.getId(), cust.getId());
        Assertions.assertEquals(customerDTO.getName(), cust.getName());
        Assertions.assertEquals(customerDTO.getPhoneNumber(), cust.getPhoneNumber());
        Assertions.assertEquals(customerDTO.getStatus(), cust.getStatus());
        Assertions.assertNull(cust.getCreatedAt());
        Assertions.assertNull(cust.getUpdatedAt());
        Assertions.assertNull(cust.getInvoice());
    }

    @Test
    void customerMapper_toCustomerShowDTO_returnCustomerShowDTO() {
        CustomerShowDTO custShowDTO = customerMapper.toCustomerShowDTO(customer);

        Assertions.assertEquals(customer.getId(), custShowDTO.getId());
        Assertions.assertEquals(customer.getName(), custShowDTO.getName());
        Assertions.assertEquals(customer.getPhoneNumber(), custShowDTO.getPhoneNumber());
    }

    @Test
    void customerMapper_toCustomerFromCustomerShowDTO_returnCustomer() {
        Customer cust = customerMapper.toCustomer(customerShowDTO);

        Assertions.assertEquals(customerShowDTO.getId(), cust.getId());
        Assertions.assertEquals(customerShowDTO.getName(), cust.getName());
        Assertions.assertEquals(customerShowDTO.getPhoneNumber(), cust.getPhoneNumber());
        Assertions.assertEquals(Status.Active, cust.getStatus());
        Assertions.assertNull(cust.getCreatedAt());
        Assertions.assertNull(cust.getUpdatedAt());
        Assertions.assertNull(cust.getInvoice());
    }

    @Test
    void customerMapper_toCustomerSaveDTO_returnCustomerSaveDTO() {
        CustomerSaveDTO custSaveDTO = customerMapper.toCustomerSaveDTO(customer);

        Assertions.assertEquals(customer.getName(), custSaveDTO.getName());
        Assertions.assertEquals(customer.getPhoneNumber(), custSaveDTO.getPhoneNumber());
    }

    @Test
    void customerMapper_toCustomerFromCustomerSaveDTO_returnCustomer() {
        Customer cust = customerMapper.toCustomer(customerSaveDTO);

        Assertions.assertEquals(customerSaveDTO.getName(), cust.getName());
        Assertions.assertEquals(customerSaveDTO.getPhoneNumber(), cust.getPhoneNumber());
        Assertions.assertEquals(Status.Active, cust.getStatus());
        Assertions.assertNull(cust.getId());
        Assertions.assertNull(cust.getCreatedAt());
        Assertions.assertNull(cust.getUpdatedAt());
        Assertions.assertNull(cust.getInvoice());
    }

    @Test
    void customerMapper_toCustomerInvoiceDTO_returnCustomerInvoiceDTO() {
        CustomerInvoiceDTO custInvoiceDTO = customerMapper.toCustomerInvoiceDTO(customer);

        Assertions.assertEquals(customer.getId(), custInvoiceDTO.getId());
        Assertions.assertEquals(customer.getName(), custInvoiceDTO.getName());
    }

    @Test
    void customerMapper_toCustomerFromCustomerInvoiceDTO_returnCustomer() {
        Customer cust = customerMapper.toCustomer(customerInvoiceDTO);

        Assertions.assertEquals(customerInvoiceDTO.getId(), cust.getId());
        Assertions.assertEquals(customerInvoiceDTO.getName(), cust.getName());
        Assertions.assertEquals(Status.Active, cust.getStatus());
        Assertions.assertNull(cust.getPhoneNumber());
        Assertions.assertNull(cust.getCreatedAt());
        Assertions.assertNull(cust.getUpdatedAt());
        Assertions.assertNull(cust.getInvoice());
    }
}
