package com.company.retailapi.viewmodel;

import com.company.retailapi.models.InvoiceItemViewModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class RetailInvoiceViewModel {
    private int invoiceId;
    @NotNull
    private Integer customerId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate purchaseDate;
    private BigDecimal total;
    @NotNull
    private List<InvoiceItemViewModel> items;

    public RetailInvoiceViewModel(@NotNull Integer customerId, LocalDate purchaseDate, @NotNull List<InvoiceItemViewModel> items) {
        this.customerId = customerId;
        this.purchaseDate = purchaseDate;
        this.items = items;
    }

    public RetailInvoiceViewModel(@NotNull Integer customerId, LocalDate purchaseDate, @NotNull List<InvoiceItemViewModel> items, BigDecimal total) {
        this.customerId = customerId;
        this.purchaseDate = purchaseDate;
        this.items = items;
        this.total = total;
    }

    public RetailInvoiceViewModel() {
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
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

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RetailInvoiceViewModel that = (RetailInvoiceViewModel) o;
        return invoiceId == that.invoiceId &&
                customerId.equals(that.customerId) &&
                purchaseDate.equals(that.purchaseDate) &&
                items.equals(that.items) &&
                Objects.equals(total, that.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceId, customerId, purchaseDate, items, total);
    }

    @Override
    public String toString() {
        return "RetailInvoiceViewModel{" +
                "invoiceId=" + invoiceId +
                ", customerId=" + customerId +
                ", purchaseDate=" + purchaseDate +
                ", total=" + total +
                ", items=" + items +
                '}';
    }
}
