package com.example.fpt_midterm_pos.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.fpt_midterm_pos.data.model.Invoice;
import com.example.fpt_midterm_pos.data.model.InvoiceDetail;
import com.example.fpt_midterm_pos.data.model.InvoiceDetailKey;
import com.example.fpt_midterm_pos.data.model.Product;
import com.example.fpt_midterm_pos.dto.InvoiceDetailDTO;
import com.example.fpt_midterm_pos.dto.InvoiceDetailSaveDTO;
import com.example.fpt_midterm_pos.mapper.InvoiceDetailMapper;


import org.mapstruct.factory.Mappers;

import java.util.*;

public class InvoiceDetailMapperTest {

    private InvoiceDetailMapper invoiceDetailMapper;

    private Product product;
    private Invoice invoice;

    private InvoiceDetail invoiceDetail;
    private InvoiceDetailDTO invoiceDetailDTO;
    private InvoiceDetailSaveDTO invoiceDetailSaveDTO;

    @BeforeEach
    void init() {
        invoiceDetailMapper = Mappers.getMapper(InvoiceDetailMapper.class);

        product = new Product();
        product.setId(UUID.randomUUID());
        product.setName("Laptop");
        invoice = new Invoice();
        invoice.setId(UUID.randomUUID());

        InvoiceDetailKey invoiceDetailkey = new InvoiceDetailKey(invoice.getId(), product.getId());

        invoiceDetail = new InvoiceDetail(invoiceDetailkey, invoice, product, product.getName(), 1, 150.99, 150.99, new Date(), new Date());
        invoiceDetailDTO = new InvoiceDetailDTO(product.getId(), product.getName(), 1, 150.99, 150.99);
        invoiceDetailSaveDTO = new InvoiceDetailSaveDTO(product.getId(), 1);
    }

    @Test
    void invoiceDetailMapper_toInvoiceDetailDTO_returnInvoiceDetailDTO() {
        InvoiceDetailDTO invcDetailDTO = invoiceDetailMapper.toInvoiceDetailDTO(invoiceDetail);

        Assertions.assertEquals(invoiceDetail.getProduct().getId(), invcDetailDTO.getProductId());
        Assertions.assertEquals(invoiceDetail.getProduct().getName(), invcDetailDTO.getProductName());
        Assertions.assertEquals(invoiceDetail.getQuantity(), invcDetailDTO.getQuantity());
        Assertions.assertEquals(invoiceDetail.getPrice(), invcDetailDTO.getPrice());
        Assertions.assertEquals(invoiceDetail.getAmount(), invcDetailDTO.getAmount());
    }

    @Test
    void invoiceDetailMapper_toInvoiceDetailFromInvoiceDetailDTO_returnInvoiceDetail() {
        InvoiceDetail invcDetail = invoiceDetailMapper.toInvoiceDetail(invoiceDetailDTO);

        Assertions.assertEquals(new InvoiceDetailKey(null, invoiceDetailDTO.getProductId()), invcDetail.getId());
        Assertions.assertEquals(invoiceDetailDTO.getProductName(), invcDetail.getProductName());
        Assertions.assertEquals(invoiceDetailDTO.getQuantity(), invcDetail.getQuantity());
        Assertions.assertEquals(invoiceDetailDTO.getPrice(), invcDetail.getPrice());
        Assertions.assertEquals(invoiceDetailDTO.getAmount(), invcDetail.getAmount());
        Assertions.assertNull(invcDetail.getInvoice());
        Assertions.assertNull(invcDetail.getProduct());
        Assertions.assertNull(invcDetail.getCreatedAt());
        Assertions.assertNull(invcDetail.getUpdatedAt());
    }

    @Test
    void invoiceDetailMapper_toInvoiceDetailSaveDTO_returnInvoiceDetailSaveDTO() {
        InvoiceDetailSaveDTO invcDetailSaveDTO = invoiceDetailMapper.toInvoiceDetailSaveDTO(invoiceDetail);

        Assertions.assertEquals(invoiceDetail.getProduct().getId(), invcDetailSaveDTO.getProductId());
        Assertions.assertEquals(invoiceDetail.getQuantity(), invcDetailSaveDTO.getQuantity());
    }

    @Test
    void invoiceDetailMapper_toInvoiceDetailFromInvoiceDetaiLSaveDTO_returnInvoiceDetail() {
        InvoiceDetail invcDetail = invoiceDetailMapper.toInvoiceDetail(invoiceDetailSaveDTO);

        Assertions.assertEquals(new InvoiceDetailKey(null, invoiceDetailSaveDTO.getProductId()), invcDetail.getId());
        Assertions.assertEquals(invoiceDetailSaveDTO.getQuantity(), invcDetail.getQuantity());
        Assertions.assertNull(invcDetail.getProductName());
        Assertions.assertNull(invcDetail.getInvoice());
        Assertions.assertNull(invcDetail.getProduct());
        Assertions.assertNull(invcDetail.getAmount());
        Assertions.assertNull(invcDetail.getPrice());
        Assertions.assertNull(invcDetail.getCreatedAt());
        Assertions.assertNull(invcDetail.getUpdatedAt());
    }
}
