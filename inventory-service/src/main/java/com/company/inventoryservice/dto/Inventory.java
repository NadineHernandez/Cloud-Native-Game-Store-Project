package com.company.inventoryservice.dto;

import java.util.Objects;

public class Inventory {
    private int inventory_id;
    private Integer product_id;
    private Integer quantity;

    public Inventory(Integer product_id, Integer quantity) {
        this.product_id = product_id;
        this.quantity = quantity;
    }

    public Inventory(){}

    public int getInventory_id() {
        return inventory_id;
    }

    public void setInventory_id(int inventory_id) {
        this.inventory_id = inventory_id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
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
        return inventory_id == inventory.inventory_id &&
                product_id.equals(inventory.product_id) &&
                quantity.equals(inventory.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inventory_id, product_id, quantity);
    }
}
