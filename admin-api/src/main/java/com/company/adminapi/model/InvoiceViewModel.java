package com.company.adminapi.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;
import java.util.Objects;

public class InvoiceViewModel {

    private Integer invoiceId;
    private Integer customerId;
    @JsonSerialize(using=LocalDateSerializer.class)
    @JsonDeserialize(using=LocalDateDeserializer.class)
    private LocalDate purchaseDate;

    public InvoiceViewModel() {
    }

    public InvoiceViewModel(Integer invoiceId, Integer customerId, LocalDate purchaseDate) {
        this.invoiceId = invoiceId;
        this.customerId = customerId;
        this.purchaseDate = purchaseDate;
    }

    public InvoiceViewModel(Integer customerId, LocalDate purchaseDate) {
        this.customerId = customerId;
        this.purchaseDate = purchaseDate;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceViewModel that = (InvoiceViewModel) o;
        return getCustomerId().equals(that.getCustomerId()) &&
                getPurchaseDate().equals(that.getPurchaseDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomerId(), getPurchaseDate());
    }
}
