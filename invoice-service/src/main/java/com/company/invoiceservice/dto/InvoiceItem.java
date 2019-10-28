package com.company.invoiceservice.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

public class InvoiceItem {
    private int invoiceItemId;
    @NotNull
    private Integer invoiceId;
    @NotNull
    private Integer inventoryId;
    @NotNull
    private Integer quantity;
    @NotNull
    @Digits(integer = 7, fraction = 2, message = "Incorrect price format")
    @Min(0)
    private BigDecimal unitPrice;

    public InvoiceItem(Integer invoiceId, Integer inventoryId, Integer quantity, BigDecimal unitPrice) {
        this.invoiceId = invoiceId;
        this.inventoryId = inventoryId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public InvoiceItem(){}

    public int getInvoiceItemId() {
        return invoiceItemId;
    }

    public void setInvoiceItemId(int invoiceItemId) {
        this.invoiceItemId = invoiceItemId;
    }

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Integer getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceItem that = (InvoiceItem) o;
        return invoiceItemId == that.invoiceItemId &&
                invoiceId.equals(that.invoiceId) &&
                inventoryId.equals(that.inventoryId) &&
                quantity.equals(that.quantity) &&
                unitPrice.equals(that.unitPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceItemId, invoiceId, inventoryId, quantity, unitPrice);
    }

    @Override
    public String toString() {
        return "InvoiceItem{" +
                "invoice_item_id=" + invoiceItemId +
                ", invoice_id=" + invoiceId +
                ", inventory_id=" + inventoryId +
                ", quantity=" + quantity +
                ", unit_price=" + unitPrice +
                '}';
    }
}
