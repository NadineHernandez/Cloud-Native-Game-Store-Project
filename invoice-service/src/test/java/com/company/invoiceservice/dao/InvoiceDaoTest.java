package com.company.invoiceservice.dao;

import com.company.invoiceservice.dto.Invoice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InvoiceDaoTest {

    @Autowired
    private InvoiceDao invoiceDao;

    @BeforeEach
    void setUp() {
        invoiceDao.getAllInvoices().stream().forEach(invoice -> {
            invoiceDao.deleteInvoice(invoice.getInvoice_id());
        });
    }

    @Test
    void createInvoice() {
        Invoice invoice = new Invoice(1, LocalDate.of(2019, 7, 22));
        invoice = invoiceDao.createInvoice(invoice);

        assertEquals(invoice, invoiceDao.getInvoice(invoice.getInvoice_id()));
    }

    @Test
    void getInvoice() {
        Invoice invoice = new Invoice(1, LocalDate.of(2019, 7, 22));
        invoice = invoiceDao.createInvoice(invoice);

        assertEquals(invoice, invoiceDao.getInvoice(invoice.getInvoice_id()));
    }

    @Test
    void getAllInvoices() {
        Invoice invoice = new Invoice(1, LocalDate.of(2019, 7, 22));
        invoiceDao.createInvoice(invoice);

        assertEquals(1, invoiceDao.getAllInvoices().size());
    }

    @Test
    void updateInvoice() {
        Invoice invoice = new Invoice(1, LocalDate.of(2019, 7, 22));
        invoice = invoiceDao.createInvoice(invoice);

        invoice.setCustomer_id(2);
        invoice.setPurchase_date(LocalDate.of(2019,8,23));
        invoiceDao.updateInvoice(invoice);

        assertEquals(invoice, invoiceDao.getInvoice(invoice.getInvoice_id()));
    }

    @Test
    void deleteInvoice() {
        Invoice invoice = new Invoice(1, LocalDate.of(2019, 7, 22));
        invoice = invoiceDao.createInvoice(invoice);
        assertEquals(invoice, invoiceDao.getInvoice(invoice.getInvoice_id()));

        invoiceDao.deleteInvoice(invoice.getInvoice_id());
        assertNull(invoiceDao.getInvoice(invoice.getInvoice_id()));
    }
}