package com.company.productservice.service;

import com.company.productservice.dao.ProductDao;
import com.company.productservice.model.Product;
import com.company.productservice.model.ProductViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceLayer {

    @Autowired
    ProductDao dao;

    public ProductViewModel createProduct(ProductViewModel pvm) {
        return buildPvm(dao.createProduct(buildProduct(pvm)));
    }

    private Product buildProduct(ProductViewModel pvm) {
        return new Product(pvm.getProductId(),pvm.getProductName(), pvm.getProductDescription(), pvm.getListPrice(), pvm.getUnitCost());
    }

    private ProductViewModel buildPvm(Product product) {
        return new ProductViewModel(product.getProductId(), product.getProductName(), product.getProductDescription(),
                product.getListPrice(), product.getUnitCost());
    }

    public Optional<ProductViewModel> findProduct(Integer id) {
        return Optional.ofNullable(dao.getProduct(id)).map(this::buildPvm);
    }

    public List<ProductViewModel> findAllProducts() {
        return dao.getAllProducts().stream().map(this::buildPvm).collect(Collectors.toList());
    }

    public void updateProduct(ProductViewModel pvm) {
        dao.updateProduct(buildProduct(pvm));
    }

    public void deleteProduct(Integer id) {
        dao.deleteProduct(id);
    }
}
