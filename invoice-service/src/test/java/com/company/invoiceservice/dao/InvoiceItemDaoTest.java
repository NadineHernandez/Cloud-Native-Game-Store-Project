package com.company.invoiceservice.dao;

import com.company.invoiceservice.dto.Invoice;
import com.company.invoiceservice.dto.InvoiceItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InvoiceItemDaoTest {

    @Autowired
    private InvoiceItemDao invoiceItemDao;

    @Autowired
    private InvoiceDao invoiceDao;

    @BeforeEach
    void setUp() {

        invoiceItemDao.getAllInvoiceItems().stream().forEach(invoiceItem -> {
            invoiceItemDao.deleteInvoiceItem(invoiceItem.getInvoiceItemId());
        });

        invoiceDao.getAllInvoices().stream().forEach(invoice -> {
            invoiceDao.deleteInvoice(invoice.getInvoiceId());
        });
    }

    @Test
    void createInvoiceItem() {
        Invoice invoice = new Invoice(1, LocalDate.of(2019,7,22));
        invoice = invoiceDao.createInvoice(invoice);
        InvoiceItem invoiceItem = new InvoiceItem(invoice.getInvoiceId(), 1, 10, new BigDecimal("10.00"));
        invoiceItem = invoiceItemDao.createInvoiceItem(invoiceItem);

        assertEquals(invoiceItem, invoiceItemDao.getInvoiceItem(invoiceItem.getInvoiceItemId()));
    }

    @Test
    void getInvoiceItem() {
        Invoice invoice = new Invoice(1, LocalDate.of(2019,7,22));
        invoice = invoiceDao.createInvoice(invoice);
        InvoiceItem invoiceItem = new InvoiceItem(invoice.getInvoiceId(), 1, 10, new BigDecimal("10.00"));
        invoiceItem = invoiceItemDao.createInvoiceItem(invoiceItem);

        assertEquals(invoiceItem, invoiceItemDao.getInvoiceItem(invoiceItem.getInvoiceItemId()));
    }

    @Test
    void getAllInvoiceItems() {
        Invoice invoice = new Invoice(1, LocalDate.of(2019,7,22));
        invoice = invoiceDao.createInvoice(invoice);
        InvoiceItem invoiceItem = new InvoiceItem(invoice.getInvoiceId(), 1, 10, new BigDecimal("10.00"));
        invoiceItemDao.createInvoiceItem(invoiceItem);

        assertEquals(1, invoiceItemDao.getAllInvoiceItems().size());
    }

    @Test
    void getInvoiceItemsByInvoiceId() {
        Invoice invoice = new Invoice(1, LocalDate.of(2019,7,22));
        invoice = invoiceDao.createInvoice(invoice);
        InvoiceItem invoiceItem = new InvoiceItem(invoice.getInvoiceId(), 1, 10, new BigDecimal("10.00"));
        invoiceItemDao.createInvoiceItem(invoiceItem);

        assertEquals(1, invoiceItemDao.getInvoiceItemsByInvoiceId(invoice.getInvoiceId()).size());
    }

    @Test
    void updateInvoiceItem() {
        Invoice invoice = new Invoice(1, LocalDate.of(2019,7,22));
        invoice = invoiceDao.createInvoice(invoice);
        InvoiceItem invoiceItem = new InvoiceItem(invoice.getInvoiceId(), 1, 10, new BigDecimal("10.00"));
        invoiceItem = invoiceItemDao.createInvoiceItem(invoiceItem);

        invoiceItem.setInventoryId(2);
        invoiceItem.setQuantity(20);
        invoiceItem.setUnitPrice(new BigDecimal("20.00"));
        invoiceItemDao.updateInvoiceItem(invoiceItem);

        assertEquals(invoiceItem, invoiceItemDao.getInvoiceItem(invoiceItem.getInvoiceItemId()));
    }

    @Test
    void deleteInvoiceItem() {
        Invoice invoice = new Invoice(1, LocalDate.of(2019,7,22));
        invoice = invoiceDao.createInvoice(invoice);
        InvoiceItem invoiceItem = new InvoiceItem(invoice.getInvoiceId(), 1, 10, new BigDecimal("10.00"));
        invoiceItem = invoiceItemDao.createInvoiceItem(invoiceItem);
        assertEquals(invoiceItem, invoiceItemDao.getInvoiceItem(invoiceItem.getInvoiceItemId()));

        invoiceItemDao.deleteInvoiceItem(invoiceItem.getInvoiceItemId());

        assertNull(invoiceItemDao.getInvoiceItem(invoiceItem.getInvoiceItemId()));
    }
}