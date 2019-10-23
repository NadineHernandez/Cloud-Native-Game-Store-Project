package com.company.invoiceservice.dto;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Invoice {
    private int invoice_id;
    private Integer customer_id;
    private LocalDate purchase_date;

    public Invoice(Integer customer_id, LocalDate purchase_date) {
        this.customer_id = customer_id;
        this.purchase_date = purchase_date;
    }

    public Invoice() {
    }

    public int getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(int invoice_id) {
        this.invoice_id = invoice_id;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public LocalDate getPurchase_date() {
        return purchase_date;
    }

    public void setPurchase_date(LocalDate purchase_date) {
        this.purchase_date = purchase_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return invoice_id == invoice.invoice_id &&
                customer_id.equals(invoice.customer_id) &&
                purchase_date.equals(invoice.purchase_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoice_id, customer_id, purchase_date);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoice_id=" + invoice_id +
                ", customer_id=" + customer_id +
                ", purchase_date=" + purchase_date +
                '}';
    }
}
