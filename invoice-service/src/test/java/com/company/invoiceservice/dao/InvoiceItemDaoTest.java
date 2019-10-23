package com.company.invoiceservice.dao;

import com.company.invoiceservice.dto.InvoiceItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InvoiceItemDaoTest {

    @Autowired
    private InvoiceItemDao invoiceItemDao;

    @BeforeEach
    void setUp() {
        invoiceItemDao.getAllInvoiceItems().stream().forEach(invoiceItem -> {
            invoiceItemDao.deleteInvoiceItem(invoiceItem.getInvoice_item_id());
        });
    }

    @Test
    void createInvoiceItem() {
        InvoiceItem invoiceItem = new InvoiceItem(1, 1, 10, new BigDecimal(10.00));
        invoiceItem = invoiceItemDao.createInvoiceItem(invoiceItem);

        assertEquals(invoiceItem, invoiceItemDao.getInvoiceItem(invoiceItem.getInvoice_item_id()));
    }

    @Test
    void getInvoiceItem() {
        InvoiceItem invoiceItem = new InvoiceItem(1, 1, 10, new BigDecimal(10.00));
        invoiceItem = invoiceItemDao.createInvoiceItem(invoiceItem);

        assertEquals(invoiceItem, invoiceItemDao.getInvoiceItem(invoiceItem.getInvoice_item_id()));
    }

    @Test
    void getAllInvoiceItems() {
        InvoiceItem invoiceItem = new InvoiceItem(1, 1, 10, new BigDecimal(10.00));
        invoiceItemDao.createInvoiceItem(invoiceItem);

        assertEquals(1, invoiceItemDao.getAllInvoiceItems().size());
    }

    @Test
    void updateInvoiceItem() {
        InvoiceItem invoiceItem = new InvoiceItem(1, 1, 10, new BigDecimal(10.00));
        invoiceItem = invoiceItemDao.createInvoiceItem(invoiceItem);

        invoiceItem.setInvoice_id(2);
        invoiceItem.setInventory_id(2);
        invoiceItem.setQuantity(20);
        invoiceItem.setUnit_price(new BigDecimal(20.00));
        invoiceItemDao.updateInvoiceItem(invoiceItem);

        assertEquals(invoiceItem, invoiceItemDao.getInvoiceItem(invoiceItem.getInvoice_item_id()));
    }

    @Test
    void deleteInvoiceItem() {
        InvoiceItem invoiceItem = new InvoiceItem(1, 1, 10, new BigDecimal(10.00));
        invoiceItem = invoiceItemDao.createInvoiceItem(invoiceItem);
        assertEquals(invoiceItem, invoiceItemDao.getInvoiceItem(invoiceItem.getInvoice_item_id()));

        invoiceItemDao.deleteInvoiceItem(invoiceItem.getInvoice_item_id());

        assertNull(invoiceItemDao.getInvoiceItem(invoiceItem.getInvoice_item_id()));
    }
}