package com.example.fpt_midterm_pos.repository;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.fpt_midterm_pos.data.repository.CustomerRepository;
import com.example.fpt_midterm_pos.data.model.Customer;
import com.example.fpt_midterm_pos.data.model.Status;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
public class CustomerRepositoryTest {
    
    @Autowired
    private CustomerRepository customerRepository;

    private Customer cust1;
    private Customer cust2;

    @BeforeEach
    void init() {
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
    void customerRepository_findByStatusPages_returnAllCustomersByStatusPages() {
        Pageable pageable = PageRequest.of(0, 20);
        Page<Customer> cust = customerRepository.findByStatus(Status.Active, pageable);

        Assertions.assertNotNull(cust);
        Assertions.assertEquals(0, cust.getNumber());
        Assertions.assertEquals(20, cust.getSize());
        Assertions.assertTrue(cust.getTotalElements() == 1);
    }

    @Test
    void customerRepository_findById_returnCustomer() {
        UUID id = cust1.getId();
        Customer cust = customerRepository.findById(id).orElse(null);

        Assertions.assertNotNull(cust);
        Assertions.assertEquals(id, cust.getId());
        Assertions.assertEquals("Alice Smith", cust.getName());
        Assertions.assertEquals("+62837194857362", cust.getPhoneNumber());
        Assertions.assertEquals(Status.Active, cust.getStatus());
        Assertions.assertTrue(cust.getCreatedAt().before(new Date()));
        Assertions.assertTrue(cust.getUpdatedAt().before(new Date()));
    }

    @Test
    void customerRepository_saveCreateCustomer_returnCustomer() {
        Customer cust = new Customer();
        cust.setName("Charlie Brown");
        cust.setPhoneNumber("+62894372638172");
        cust.setStatus(Status.Active);
        cust.setCreatedAt(new Date());
        cust.setUpdatedAt(new Date());
        
        Customer savedCust = customerRepository.save(cust);

        Assertions.assertNotNull(savedCust);
        Assertions.assertNotNull(savedCust.getId());
        Assertions.assertEquals("Charlie Brown", savedCust.getName());
        Assertions.assertEquals("+62894372638172", savedCust.getPhoneNumber());
        Assertions.assertEquals(Status.Active, savedCust.getStatus());
        Assertions.assertTrue(savedCust.getCreatedAt().before(new Date()));
        Assertions.assertTrue(savedCust.getUpdatedAt().before(new Date()));
    }

    @Test
    void customerRepository_saveUpdateCustomer_returnUpdatedCustomer() {
        UUID id = cust1.getId();
        Customer cust = customerRepository.findById(id).orElse(null);
        cust.setName("Alice Bobby");
        cust.setPhoneNumber("+62837194857362");
        cust.setUpdatedAt(new Date());

        Customer updatedCustomer = customerRepository.save(cust);

        Assertions.assertNotNull(updatedCustomer);
        Assertions.assertEquals(id, updatedCustomer.getId());
        Assertions.assertEquals("Alice Bobby", updatedCustomer.getName());
        Assertions.assertEquals("+62837194857362", updatedCustomer.getPhoneNumber());
        Assertions.assertTrue(updatedCustomer.getUpdatedAt().after(updatedCustomer.getCreatedAt()));
    }

    @Test
    void customerRepository_saveUpdateCustomerStatus_returnUpdateCustomerStatus() {
        UUID id = cust1.getId();
        Customer cust = customerRepository.findById(id).orElse(null);
        cust.setStatus(Status.Deactive);
        cust.setUpdatedAt(new Date());

        Customer updatedCustomer = customerRepository.save(cust);

        Assertions.assertNotNull(updatedCustomer);
        Assertions.assertEquals(id, updatedCustomer.getId());
        Assertions.assertEquals(Status.Deactive, updatedCustomer.getStatus());
        Assertions.assertTrue(updatedCustomer.getUpdatedAt().after(updatedCustomer.getCreatedAt()));
    }
}
