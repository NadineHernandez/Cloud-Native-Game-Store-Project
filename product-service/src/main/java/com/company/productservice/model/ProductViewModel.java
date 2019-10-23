package com.company.productservice.model;

import java.math.BigDecimal;
import java.util.Objects;

public class ProductViewModel {

    private Integer productId;
    private String productName;
    private String productDescription;
    private BigDecimal listPrice;
    private BigDecimal unitCost;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public BigDecimal getListPrice() {
        return listPrice;
    }

    public void setListPrice(BigDecimal listPrice) {
        this.listPrice = listPrice;
    }

    public BigDecimal getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(BigDecimal unitCost) {
        this.unitCost = unitCost;
    }

    public ProductViewModel() {

    }

    public ProductViewModel(Integer productId, String productName, String productDescription, BigDecimal listPrice, BigDecimal unitCost) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.listPrice = listPrice;
        this.unitCost = unitCost;
    }

    public ProductViewModel(String productName, String productDescription, BigDecimal listPrice, BigDecimal unitCost) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.listPrice = listPrice;
        this.unitCost = unitCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductViewModel that = (ProductViewModel) o;
        return getProductName().equals(that.getProductName()) &&
                getProductDescription().equals(that.getProductDescription()) &&
                getListPrice().equals(that.getListPrice()) &&
                getUnitCost().equals(that.getUnitCost());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductName(), getProductDescription(), getListPrice(), getUnitCost());
    }

    @Override
    public String toString() {
        return "ProductViewModel{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", listPrice=" + listPrice +
                ", unitCost=" + unitCost +
                '}';
    }
}
