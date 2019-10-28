package com.company.adminapi.model;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class InventoryViewModel {

    private Integer inventoryId;
    @NotNull
    private Integer productId;
    @NotNull
    private Integer quantity;

    public InventoryViewModel() {
    }

    public InventoryViewModel(Integer inventoryId, @NotNull Integer productId, @NotNull Integer quantity) {
        this.inventoryId = inventoryId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public InventoryViewModel(@NotNull Integer productId, @NotNull Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Integer getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Integer inventoryId) {
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
        return getProductId().equals(that.getProductId()) &&
                getQuantity().equals(that.getQuantity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductId(), getQuantity());
    }

    @Override
    public String toString() {
        return "InventoryViewModel{" +
                "inventoryId=" + inventoryId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
