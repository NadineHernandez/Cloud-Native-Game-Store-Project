package com.company.invoiceservice.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

public class InvoiceItem {
    private int invoice_item_id;
    @NotNull
    private Integer invoice_id;
    @NotNull
    private Integer inventory_id;
    @NotNull
    private Integer quantity;
    @NotNull
    @Digits(integer = 7, fraction = 2, message = "Incorrect price format")
    @Min(0)
    private BigDecimal unit_price;

    public InvoiceItem(Integer invoice_id, Integer inventory_id, Integer quantity, BigDecimal unit_price) {
        this.invoice_id = invoice_id;
        this.inventory_id = inventory_id;
        this.quantity = quantity;
        this.unit_price = unit_price;
    }

    public InvoiceItem(){}

    public int getInvoice_item_id() {
        return invoice_item_id;
    }

    public void setInvoice_item_id(int invoice_item_id) {
        this.invoice_item_id = invoice_item_id;
    }

    public Integer getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(Integer invoice_id) {
        this.invoice_id = invoice_id;
    }

    public Integer getInventory_id() {
        return inventory_id;
    }

    public void setInventory_id(Integer inventory_id) {
        this.inventory_id = inventory_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(BigDecimal unit_price) {
        this.unit_price = unit_price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceItem that = (InvoiceItem) o;
        return invoice_item_id == that.invoice_item_id &&
                invoice_id.equals(that.invoice_id) &&
                inventory_id.equals(that.inventory_id) &&
                quantity.equals(that.quantity) &&
                unit_price.equals(that.unit_price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoice_item_id, invoice_id, inventory_id, quantity, unit_price);
    }

    @Override
    public String toString() {
        return "InvoiceItem{" +
                "invoice_item_id=" + invoice_item_id +
                ", invoice_id=" + invoice_id +
                ", inventory_id=" + inventory_id +
                ", quantity=" + quantity +
                ", unit_price=" + unit_price +
                '}';
    }
}
