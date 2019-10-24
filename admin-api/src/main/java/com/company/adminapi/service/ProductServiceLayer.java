package com.company.adminapi.service;

import com.company.adminapi.model.ProductViewModel;
import com.company.adminapi.util.feign.ProductClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceLayer {

    @Autowired
    ProductClient client;

    public Optional<ProductViewModel> findProduct(int id) {
        return null;
    }

    public List<ProductViewModel> findAllProducts() {
        return null;
    }

    public ProductViewModel createProduct(ProductViewModel pvm) {
        return null;
    }

    public void updateProduct(ProductViewModel pvm) {

    }

    public void deleteProduct(int id) {

    }

}
