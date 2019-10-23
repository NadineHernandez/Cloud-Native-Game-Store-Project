package com.company.invoiceservice.dao;

import com.company.invoiceservice.dto.InvoiceItem;

import java.util.List;

public interface InvoiceItemDao {
    InvoiceItem createInvoiceItem(InvoiceItem invoiceItem);
    InvoiceItem getInvoiceItem(int id);
    List<InvoiceItem> getAllInvoiceItems();
    void updateInvoiceItem(InvoiceItem invoiceItem);
    void deleteInvoiceItem(int id);
}
