package com.company.productservice.dao;

import com.company.productservice.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class ProductDaoTest {

    @Autowired
    ProductDao dao;

    @BeforeEach
    void setUp() {
        dao.getAllProducts().forEach(product -> dao.deleteProduct(product.getProductId()));
    }

    @Test
    void createProduct() {
        Product product = new Product("Chair", "Sit On", new BigDecimal("9.99"), new BigDecimal("0.99"));
        product = dao.createProduct(product);
        assertEquals(product, dao.getProduct(product.getProductId()));

    }

    @Test
    void getProduct() {
        Product product = new Product("Chair", "Sit On", new BigDecimal("9.99"), new BigDecimal("0.99"));
        product = dao.createProduct(product);
        assertEquals(product, dao.getProduct(product.getProductId()));

    }

    @Test
    void getAllProducts() {
        Product product = new Product("Chair", "Sit On", new BigDecimal("9.99"), new BigDecimal("0.99"));
        product = dao.createProduct(product);
        List<Product> products = Collections.singletonList(product);
        assertEquals(products, dao.getAllProducts());

    }

    @Test
    void updateProduct() {
        Product product = new Product("Chair", "Sit On", new BigDecimal("9.99"), new BigDecimal("0.99"));
        product = dao.createProduct(product);
        product.setProductDescription("Sit On Gently");
        dao.updateProduct(product);
        assertEquals(product, dao.getProduct(product.getProductId()));
    }

    @Test
    void deleteProduct() {
        Product product = new Product("Chair", "Sit On", new BigDecimal("9.99"), new BigDecimal("0.99"));
        product = dao.createProduct(product);
        dao.deleteProduct(product.getProductId());
        assertNull(dao.getProduct(product.getProductId()));

    }
}