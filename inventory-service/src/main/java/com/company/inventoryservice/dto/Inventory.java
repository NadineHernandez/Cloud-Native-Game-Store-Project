package com.company.inventoryservice.dto;

import java.util.Objects;

public class Inventory {
    private int inventoryId;
    private Integer productId;
    private Integer quantity;

    public Inventory(Integer product_id, Integer quantity) {
        this.productId = product_id;
        this.quantity = quantity;
    }

    public Inventory(){}

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
        Inventory inventory = (Inventory) o;
        return inventoryId == inventory.inventoryId &&
                productId.equals(inventory.productId) &&
                quantity.equals(inventory.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inventoryId, productId, quantity);
    }
}
