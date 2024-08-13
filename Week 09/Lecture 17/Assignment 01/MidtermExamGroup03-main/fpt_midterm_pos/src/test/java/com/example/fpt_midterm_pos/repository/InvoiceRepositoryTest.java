package com.example.fpt_midterm_pos.repository;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.fpt_midterm_pos.data.repository.CustomerRepository;
import com.example.fpt_midterm_pos.data.repository.InvoiceRepository;
import com.example.fpt_midterm_pos.data.model.Customer;
import com.example.fpt_midterm_pos.data.model.Invoice;
import com.example.fpt_midterm_pos.data.model.Status;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
public class InvoiceRepositoryTest {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private CustomerRepository customerRepository;

    private Customer cust1;
    private Customer cust2;

    private Invoice invc1;
    private Invoice invc2;

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

        invc1 = new Invoice();
        invc1.setAmount(63.0);
        invc1.setCreatedAt(new Date());
        invc1.setDate(new Date());
        invc1.setUpdatedAt(new Date());
        invc1.setCustomer(cust1);
        invoiceRepository.save(invc1);

        invc2 = new Invoice();
        invc2.setAmount(26.0);
        invc2.setCreatedAt(new Date());
        invc2.setDate(new Date());
        invc2.setUpdatedAt(new Date());
        invc2.setCustomer(cust2);
        invoiceRepository.save(invc2);
    }

    @Test
    void invoiceRepository_findByFilterPages_returnAllInvoiceWithPages() {
        Pageable pageable = PageRequest.of(0, 20);
        Page<Invoice> invcs = invoiceRepository.findByFilters(
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            pageable
        );

        Assertions.assertNotNull(invcs);
        Assertions.assertEquals(0, invcs.getNumber());
        Assertions.assertEquals(20, invcs.getSize());
        Assertions.assertTrue(invcs.getTotalElements() == 2);
    }

    @Test
    void invoiceRepository_saveCreateInvoice_returnInvoice() {
        Customer cust = new Customer();
        cust.setName("Charlie Go");
        cust.setPhoneNumber("+6283641927485");
        cust.setStatus(Status.Active);
        cust.setCreatedAt(new Date());
        cust.setUpdatedAt(new Date());
        customerRepository.save(cust);

        Invoice invc = new Invoice();
        invc.setAmount(78.0);
        invc.setCreatedAt(new Date());
        invc.setDate(new Date());
        invc.setUpdatedAt(new Date());
        invc.setCustomer(cust);

        Invoice savedInvoice = invoiceRepository.save(invc);

        Assertions.assertNotNull(savedInvoice);
        Assertions.assertNotNull(savedInvoice.getId());
        Assertions.assertNotNull(savedInvoice.getCustomer());
        Assertions.assertEquals(78.0, savedInvoice.getAmount());
        Assertions.assertTrue(savedInvoice.getDate().before(new Date()));
        Assertions.assertTrue(savedInvoice.getCreatedAt().before(new Date()));
        Assertions.assertTrue(savedInvoice.getUpdatedAt().before(new Date()));
    }

    @Test
    void invoiceRepository_findInvoiceById_returnInvoice() {
        UUID id = invc1.getId();
        Invoice invc = invoiceRepository.findById(id).orElse(null);

        Assertions.assertNotNull(invc);
        Assertions.assertNotNull(invc.getCustomer());
        Assertions.assertEquals(id, invc.getId());
        Assertions.assertEquals(63.0, invc.getAmount());
        Assertions.assertTrue(invc.getDate().before(new Date()));
        Assertions.assertTrue(invc.getCreatedAt().before(new Date()));
        Assertions.assertTrue(invc.getUpdatedAt().before(new Date()));
    }

    @Test
    void invoiceRepository_saveUpdateInvoice_returnUpdatedInvoice() {
        UUID id = invc1.getId();
        Invoice invc = invoiceRepository.findById(id).orElse(null);
        invc.setAmount(80.0);
        invc.setUpdatedAt(new Date());

        Invoice updatedInv = invoiceRepository.save(invc);

        Assertions.assertNotNull(updatedInv);
        Assertions.assertNotNull(updatedInv.getCustomer());
        Assertions.assertEquals(id, updatedInv.getId());
        Assertions.assertEquals(80.0, updatedInv.getAmount());
        Assertions.assertTrue(updatedInv.getUpdatedAt().after(updatedInv.getCreatedAt()));
    }

    @Test
    void invoiceRepository_findInvoiceByFilterForExcel_returnInvoices() {
        List<Invoice> invcs = invoiceRepository.findByFiltersForExcel(
            null,
            null,
            null
        );

        Assertions.assertNotNull(invcs);
        Assertions.assertEquals(0, invcs.size());
    }

    @Test
    void invoiceRepository_findTotalRevenueByYear_returnAmountRevenue() {
        double amount = invoiceRepository.findTotalRevenueByYear(2024);

        Assertions.assertEquals(89.0, amount);
    }

    @Test
    void invoiceRepository_findTotalRevenueByMonth_returnAmountRevenue() {
        double amount = invoiceRepository.findTotalRevenueByMonth(2024, 8);
        
        Assertions.assertEquals(89.0, amount);
    }
}
