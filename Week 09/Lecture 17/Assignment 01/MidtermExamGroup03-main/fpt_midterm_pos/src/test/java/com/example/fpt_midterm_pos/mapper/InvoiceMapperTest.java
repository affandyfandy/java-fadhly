package com.example.fpt_midterm_pos.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.fpt_midterm_pos.data.model.Customer;
import com.example.fpt_midterm_pos.data.model.Invoice;
import com.example.fpt_midterm_pos.dto.CustomerInvoiceDTO;
import com.example.fpt_midterm_pos.dto.InvoiceDTO;
import com.example.fpt_midterm_pos.dto.InvoiceSaveDTO;
import com.example.fpt_midterm_pos.mapper.InvoiceMapper;

import org.mapstruct.factory.Mappers;

import java.util.*;

public class InvoiceMapperTest {

    private InvoiceMapper invoiceMapper;

    private Customer customer;
    private CustomerInvoiceDTO customerInvoiceDTO;

    private Invoice invoice;
    private InvoiceDTO invoiceDTO;
    private InvoiceSaveDTO invoiceSaveDTO;

    @BeforeEach
    void init() {
        invoiceMapper = Mappers.getMapper(InvoiceMapper.class);

        customer = new Customer();
        customer.setId(UUID.randomUUID());
        customer.setName("Alex Korji");
        customerInvoiceDTO = new CustomerInvoiceDTO();
        customerInvoiceDTO.setId(customer.getId());
        customerInvoiceDTO.setName(customer.getName());

        invoice = new Invoice(UUID.randomUUID(), 10.0, new Date(), new Date(), new Date(), customer, null);
        invoiceDTO = new InvoiceDTO(UUID.randomUUID(), 10.0, new Date(), customerInvoiceDTO, null);
        invoiceSaveDTO = new InvoiceSaveDTO(customer.getId(), null);
    }

    @Test
    void invoiceMapper_toInvoiceDTO_returntoInvoiceDTO() {
        InvoiceDTO invcDTO = invoiceMapper.toInvoiceDTO(invoice);

        Assertions.assertEquals(invoice.getId(), invcDTO.getId());
        Assertions.assertEquals(invoice.getAmount(), invcDTO.getAmount());
        Assertions.assertEquals(invoice.getDate(), invcDTO.getDate());
        Assertions.assertEquals(invoice.getCustomer().getId(), invcDTO.getCustomer().getId());
        Assertions.assertEquals(invoice.getInvoiceDetails(), invcDTO.getInvoiceDetails());
    }

    @Test
    void invoiceMapper_toInvoiceFromInvoiceDTO_returnInvoice() {
        Invoice invc = invoiceMapper.toInvoice(invoiceDTO);

        Assertions.assertEquals(invoiceDTO.getId(), invc.getId());
        Assertions.assertEquals(invoiceDTO.getCustomer().getId(), invc.getCustomer().getId());
        Assertions.assertEquals(invoiceDTO.getInvoiceDetails(), invc.getInvoiceDetails());
        Assertions.assertNull(invc.getAmount());
        Assertions.assertNull(invc.getDate());
        Assertions.assertNull(invc.getCreatedAt());
        Assertions.assertNull(invc.getUpdatedAt());
    }

    @Test
    void invoiceMapper_toInvoiceSaveDTO_returnToInvoiceSaveDTO() {
        InvoiceSaveDTO invcSaveDTO = invoiceMapper.toInvoiceSaveDTO(invoice);

        Assertions.assertEquals(invoice.getCustomer().getId(), invcSaveDTO.getCustomerId());
        Assertions.assertEquals(invoice.getInvoiceDetails(), invcSaveDTO.getInvoiceDetails());
    }

    @Test
    void invoiceMapper_toInvoiceFromInvoiceSaveDTO_returnInvoice() {
        Invoice invc = invoiceMapper.toInvoice(invoiceSaveDTO);

        Assertions.assertEquals(invoiceSaveDTO.getCustomerId(), invc.getCustomer().getId());
        Assertions.assertEquals(invoiceSaveDTO.getInvoiceDetails(), invc.getInvoiceDetails());
        Assertions.assertNull(invc.getId());
        Assertions.assertNull(invc.getAmount());
        Assertions.assertNull(invc.getDate());
        Assertions.assertNull(invc.getCreatedAt());
        Assertions.assertNull(invc.getUpdatedAt());
    }
}
