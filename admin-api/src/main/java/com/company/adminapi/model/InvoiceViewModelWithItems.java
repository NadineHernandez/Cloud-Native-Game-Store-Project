package com.company.adminapi.model;

import java.time.LocalDate;
import java.util.List;

public class InvoiceViewModelWithItems {

    private Integer invoiceId;
    private Integer customerId;
    private LocalDate purchaseDate;
    private List<InvoiceItemViewModel> items;

    public InvoiceViewModelWithItems() {
    }

    public InvoiceViewModelWithItems(Integer invoiceId, Integer customerId, LocalDate purchaseDate, List<InvoiceItemViewModel> items) {
        this.invoiceId = invoiceId;
        this.customerId = customerId;
        this.purchaseDate = purchaseDate;
        this.items = items;
    }

    public InvoiceViewModelWithItems(Integer customerId, LocalDate purchaseDate, List<InvoiceItemViewModel> items) {
        this.customerId = customerId;
        this.purchaseDate = purchaseDate;
        this.items = items;
    }

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public List<InvoiceItemViewModel> getItems() {
        return items;
    }

    public void setItems(List<InvoiceItemViewModel> items) {
        this.items = items;
    }


}
