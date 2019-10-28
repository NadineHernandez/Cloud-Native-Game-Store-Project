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

        return client.getProduct(id);
    }

    public List<ProductViewModel> findAllProducts() {

        return client.getAllProducts();
    }

    public ProductViewModel createProduct(ProductViewModel pvm) {

        return client.addProduct(pvm);
    }

    public void updateProduct(ProductViewModel pvm) {
        client.updateProduct(pvm);
    }

    public void deleteProduct(int id) {
        client.deleteProduct(id);
    }

}
