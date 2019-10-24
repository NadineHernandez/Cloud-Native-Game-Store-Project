package com.company.retailapi.models;


import javax.validation.constraints.NotNull;
import java.util.Objects;

public class InventoryViewModel {
    private int inventoryId;
    @NotNull
    private Integer productId;
    @NotNull
    private Integer quantity;

    public InventoryViewModel(Integer product_id, Integer quantity) {
        this.productId = product_id;
        this.quantity = quantity;
    }

    public InventoryViewModel(){}

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryViewModel that = (InventoryViewModel) o;
        return inventoryId == that.inventoryId &&
                productId.equals(that.productId) &&
                quantity.equals(that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inventoryId, productId, quantity);
    }
}