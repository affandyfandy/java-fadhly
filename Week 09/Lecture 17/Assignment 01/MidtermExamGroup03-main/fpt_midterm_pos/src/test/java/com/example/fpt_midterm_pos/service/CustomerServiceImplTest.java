package com.example.fpt_midterm_pos.service;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.fpt_midterm_pos.data.model.Customer;
import com.example.fpt_midterm_pos.data.model.Status;
import com.example.fpt_midterm_pos.mapper.CustomerMapper;
import com.example.fpt_midterm_pos.dto.CustomerDTO;
import com.example.fpt_midterm_pos.dto.CustomerShowDTO;
import com.example.fpt_midterm_pos.dto.CustomerSaveDTO;
import com.example.fpt_midterm_pos.exception.DuplicateStatusException;
import com.example.fpt_midterm_pos.exception.ResourceNotFoundException;
import com.example.fpt_midterm_pos.data.repository.CustomerRepository;
import com.example.fpt_midterm_pos.service.impl.CustomerServiceImpl;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
public class CustomerServiceImplTest {
    
    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    CustomerServiceImpl customerService;

    @Mock
    CustomerMapper customerMapper;

    private Customer cust1;
    private Customer cust2;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);

        cust1 = new Customer();
        cust1.setName("Alice Smith");
        cust1.setPhoneNumber("+62837194857362");
        cust1.setStatus(Status.Active);
        cust1.setCreatedAt(new Date());
        cust1.setUpdatedAt(new Date());
        customerRepository.save(cust1);

        cust2 = new Customer();
        cust2.setName("Bob Johnson");
        cust2.setPhoneNumber("+62637482637482");
        cust2.setStatus(Status.Deactive);
        cust2.setCreatedAt(new Date());
        cust2.setUpdatedAt(new Date());
        customerRepository.save(cust2);
    }

    @Test
    void customerServiceImpl_findAllActiveCustomer_returnCustomerShowDTO() {
        Pageable pageable = PageRequest.of(0, 20);
        Page<Customer> customerPage = new PageImpl<>(Arrays.asList(cust1));

        CustomerShowDTO customerShowDTO = new CustomerShowDTO();
        customerShowDTO.setId(cust1.getId());
        customerShowDTO.setName(cust1.getName());
        customerShowDTO.setPhoneNumber(cust1.getPhoneNumber());

        when(customerRepository.findByStatus(Status.Active, pageable)).thenReturn(customerPage);
        when(customerMapper.toCustomerShowDTO(cust1)).thenReturn(customerShowDTO);

        Page<CustomerShowDTO> result = customerService.findAllActiveCustomer(pageable);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.getTotalElements());
        Assertions.assertEquals(cust1.getName(), result.getContent().get(0).getName());
        Assertions.assertEquals(customerShowDTO, result.getContent().get(0));
    }

    @Test
    void customerServiceImpl_findById_returnCustomerFound() {
        when(customerRepository.findById(cust1.getId())).thenReturn(Optional.of(cust1));

        Customer result = customerService.findById(cust1.getId());

        Assertions.assertNotNull(result);
        Assertions.assertEquals(cust1, result);
    }

    @Test
    void customerServiceImpl_findById_returnCustomerNotFound() {
        UUID randomId = UUID.randomUUID();
        when(customerRepository.findById(randomId)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> customerService.findById(randomId));
    }

    @Test
    void customerServiceImpl_createCustomer_returnCustomerDTO() {
        CustomerSaveDTO custSaveDTO = new CustomerSaveDTO();
        custSaveDTO.setName("Charlie Brown");
        custSaveDTO.setPhoneNumber("+62894372638172");

        Customer cust = new Customer();
        cust.setId(UUID.randomUUID());
        cust.setName(custSaveDTO.getName());
        cust.setPhoneNumber(custSaveDTO.getPhoneNumber());
        cust.setCreatedAt(new Date());
        cust.setUpdatedAt(new Date());

        CustomerDTO custDTO = new CustomerDTO();
        custDTO.setId(cust.getId());
        custDTO.setName(cust.getName());
        custDTO.setPhoneNumber(cust.getPhoneNumber());
        custDTO.setStatus(cust.getStatus());

        when(customerMapper.toCustomer(custSaveDTO)).thenReturn(cust);
        when(customerRepository.save(cust)).thenReturn(cust);
        when(customerMapper.toCustomerDTO(cust)).thenReturn(custDTO);

        CustomerDTO result = customerService.createCustomer(custSaveDTO);

        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getId());
        Assertions.assertEquals(custSaveDTO.getName(), result.getName());
        Assertions.assertEquals(custSaveDTO.getPhoneNumber(), result.getPhoneNumber());
        Assertions.assertEquals(Status.Active, result.getStatus());
    }

    @Test
    void customerServiceImpl_updateCustomer_returnCustomerDTO() {
        UUID id = cust1.getId();
        CustomerSaveDTO custUpdateDTO = new CustomerSaveDTO();
        custUpdateDTO.setName("Alice Bobby");
        custUpdateDTO.setPhoneNumber("+62837194857362");

        CustomerDTO custDTO = new CustomerDTO();
        custDTO.setId(cust1.getId());
        custDTO.setName(custUpdateDTO.getName());
        custDTO.setPhoneNumber(custUpdateDTO.getPhoneNumber());
        custDTO.setStatus(cust1.getStatus());

        when(customerRepository.findById(id)).thenReturn(Optional.of(cust1));
        when(customerMapper.toCustomer(custUpdateDTO)).thenReturn(cust1);
        when(customerRepository.save(cust1)).thenReturn(cust1);
        when(customerMapper.toCustomerDTO(cust1)).thenReturn(custDTO);

        CustomerDTO result = customerService.updateCustomer(id, custUpdateDTO);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(cust1.getId(), result.getId());
        Assertions.assertEquals(custUpdateDTO.getName(), result.getName());
        Assertions.assertEquals(custUpdateDTO.getPhoneNumber(), result.getPhoneNumber());
    }

    @Test
    void customerServiceImpl_updateCustomerStatus_returnStatusActiveChangeCustomerDTO() {
        UUID id = cust1.getId();

        CustomerDTO custDTO = new CustomerDTO();
        custDTO.setId(cust1.getId());
        custDTO.setName(cust1.getName());
        custDTO.setPhoneNumber(cust1.getPhoneNumber());
        custDTO.setStatus(Status.Deactive);

        when(customerRepository.findById(id)).thenReturn(Optional.of(cust1));
        when(customerRepository.save(cust1)).thenReturn(cust1);
        when(customerMapper.toCustomerDTO(cust1)).thenReturn(custDTO);

        CustomerDTO result = customerService.updateCustomerStatus(id, Status.Deactive);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(cust1.getId(), result.getId());
        Assertions.assertEquals(Status.Deactive, result.getStatus());
    }

    @Test
    void customerServiceImpl_updateCustomerStatus_returnStatusDeactiveChangeCustomerDTO() {
        UUID id = cust2.getId();

        CustomerDTO custDTO = new CustomerDTO();
        custDTO.setId(cust2.getId());
        custDTO.setName(cust2.getName());
        custDTO.setPhoneNumber(cust2.getPhoneNumber());
        custDTO.setStatus(Status.Active);

        when(customerRepository.findById(id)).thenReturn(Optional.of(cust2));
        when(customerRepository.save(cust2)).thenReturn(cust2);
        when(customerMapper.toCustomerDTO(cust2)).thenReturn(custDTO);

        CustomerDTO result = customerService.updateCustomerStatus(id, Status.Active);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(cust1.getId(), result.getId());
        Assertions.assertEquals(Status.Active, result.getStatus());
    }

    @Test
    void customerServiceImpl_updateCustomerStatus_returnDuplicateStatus() {
        UUID id = cust1.getId();

        when(customerRepository.findById(id)).thenReturn(Optional.of(cust1));

        Assertions.assertThrows(DuplicateStatusException.class, () -> customerService.updateCustomerStatus(id, Status.Active));
    }
}
