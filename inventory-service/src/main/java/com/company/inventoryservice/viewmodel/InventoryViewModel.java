package com.company.inventoryservice.viewmodel;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class InventoryViewModel {
    private int inventory_id;
    @NotNull
    private Integer product_id;
    @NotNull
    private Integer quantity;

    public InventoryViewModel(Integer product_id, Integer quantity) {
        this.product_id = product_id;
        this.quantity = quantity;
    }

    public InventoryViewModel(){}

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
        InventoryViewModel that = (InventoryViewModel) o;
        return inventory_id == that.inventory_id &&
                product_id.equals(that.product_id) &&
                quantity.equals(that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inventory_id, product_id, quantity);
    }
}