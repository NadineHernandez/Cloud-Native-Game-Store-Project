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
            invoiceItemDao.deleteInvoiceItem(invoiceItem.getInvoice_item_id());
        });

        invoiceDao.getAllInvoices().stream().forEach(invoice -> {
            invoiceDao.deleteInvoice(invoice.getInvoice_id());
        });
    }

    @Test
    void createInvoiceItem() {
        Invoice invoice = new Invoice(1, LocalDate.of(2019,7,22));
        invoice = invoiceDao.createInvoice(invoice);
        InvoiceItem invoiceItem = new InvoiceItem(invoice.getInvoice_id(), 1, 10, new BigDecimal("10.00"));
        invoiceItem = invoiceItemDao.createInvoiceItem(invoiceItem);

        assertEquals(invoiceItem, invoiceItemDao.getInvoiceItem(invoiceItem.getInvoice_item_id()));
    }

    @Test
    void getInvoiceItem() {
        Invoice invoice = new Invoice(1, LocalDate.of(2019,7,22));
        invoice = invoiceDao.createInvoice(invoice);
        InvoiceItem invoiceItem = new InvoiceItem(invoice.getInvoice_id(), 1, 10, new BigDecimal("10.00"));
        invoiceItem = invoiceItemDao.createInvoiceItem(invoiceItem);

        assertEquals(invoiceItem, invoiceItemDao.getInvoiceItem(invoiceItem.getInvoice_item_id()));
    }

    @Test
    void getAllInvoiceItems() {
        Invoice invoice = new Invoice(1, LocalDate.of(2019,7,22));
        invoice = invoiceDao.createInvoice(invoice);
        InvoiceItem invoiceItem = new InvoiceItem(invoice.getInvoice_id(), 1, 10, new BigDecimal("10.00"));
        invoiceItemDao.createInvoiceItem(invoiceItem);

        assertEquals(1, invoiceItemDao.getAllInvoiceItems().size());
    }

    @Test
    void getInvoiceItemsByInvoiceId() {
        Invoice invoice = new Invoice(1, LocalDate.of(2019,7,22));
        invoice = invoiceDao.createInvoice(invoice);
        InvoiceItem invoiceItem = new InvoiceItem(invoice.getInvoice_id(), 1, 10, new BigDecimal("10.00"));
        invoiceItemDao.createInvoiceItem(invoiceItem);

        assertEquals(1, invoiceItemDao.getInvoiceItemsByInvoiceId(invoice.getInvoice_id()).size());
    }

    @Test
    void updateInvoiceItem() {
        Invoice invoice = new Invoice(1, LocalDate.of(2019,7,22));
        invoice = invoiceDao.createInvoice(invoice);
        InvoiceItem invoiceItem = new InvoiceItem(invoice.getInvoice_id(), 1, 10, new BigDecimal("10.00"));
        invoiceItem = invoiceItemDao.createInvoiceItem(invoiceItem);

        invoiceItem.setInventory_id(2);
        invoiceItem.setQuantity(20);
        invoiceItem.setUnit_price(new BigDecimal("20.00"));
        invoiceItemDao.updateInvoiceItem(invoiceItem);

        assertEquals(invoiceItem, invoiceItemDao.getInvoiceItem(invoiceItem.getInvoice_item_id()));
    }

    @Test
    void deleteInvoiceItem() {
        Invoice invoice = new Invoice(1, LocalDate.of(2019,7,22));
        invoice = invoiceDao.createInvoice(invoice);
        InvoiceItem invoiceItem = new InvoiceItem(invoice.getInvoice_id(), 1, 10, new BigDecimal("10.00"));
        invoiceItem = invoiceItemDao.createInvoiceItem(invoiceItem);
        assertEquals(invoiceItem, invoiceItemDao.getInvoiceItem(invoiceItem.getInvoice_item_id()));

        invoiceItemDao.deleteInvoiceItem(invoiceItem.getInvoice_item_id());

        assertNull(invoiceItemDao.getInvoiceItem(invoiceItem.getInvoice_item_id()));
    }
}