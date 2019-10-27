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
            invoiceDao.deleteInvoice(invoice.getInvoiceId());
        });
    }

    @Test
    void createInvoice() {
        Invoice invoice = new Invoice(1, LocalDate.of(2019, 7, 22));
        invoice = invoiceDao.createInvoice(invoice);

        assertEquals(invoice, invoiceDao.getInvoice(invoice.getInvoiceId()));
    }

    @Test
    void getInvoice() {
        Invoice invoice = new Invoice(1, LocalDate.of(2019, 7, 22));
        invoice = invoiceDao.createInvoice(invoice);

        assertEquals(invoice, invoiceDao.getInvoice(invoice.getInvoiceId()));
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

        invoice.setCustomerId(2);
        invoice.setPurchaseDate(LocalDate.of(2019,8,23));
        invoiceDao.updateInvoice(invoice);

        assertEquals(invoice, invoiceDao.getInvoice(invoice.getInvoiceId()));
    }

    @Test
    void deleteInvoice() {
        Invoice invoice = new Invoice(1, LocalDate.of(2019, 7, 22));
        invoice = invoiceDao.createInvoice(invoice);
        assertEquals(invoice, invoiceDao.getInvoice(invoice.getInvoiceId()));

        invoiceDao.deleteInvoice(invoice.getInvoiceId());
        assertNull(invoiceDao.getInvoice(invoice.getInvoiceId()));
    }

    @Test
    void getInvoicesByCustomerId(){
        Invoice invoice = new Invoice(1, LocalDate.of(2019, 7, 22));
        invoiceDao.createInvoice(invoice);

        assertEquals(1, invoiceDao.getInvoicesByCustomerId(1).size());
    }
}