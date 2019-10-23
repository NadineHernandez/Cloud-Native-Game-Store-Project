package com.company.productservice.dao;

import com.company.productservice.model.Product;

import java.util.List;

public interface ProductDao {

    Product createProduct(Product product);
    Product getProduct(Integer id);
    List<Product> getAllProducts();
    void updateProduct(Product product);
    void deleteProduct(Integer id);

}
