package com.company.invoiceservice.dao;

import com.company.invoiceservice.dto.Invoice;

import java.util.List;

public interface InvoiceDao {
    Invoice createInvoice(Invoice invoice);
    Invoice getInvoice(int id);
    List<Invoice> getAllInvoices();
    void updateInvoice(Invoice invoice);
    void deleteInvoice(int id);
}
