package com.company.productservice.service;

import com.company.productservice.dao.ProductDao;
import com.company.productservice.model.Product;
import com.company.productservice.model.ProductViewModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceLayerTest {

    @InjectMocks
    ProductServiceLayer service;

    @Mock
    ProductDao dao;

    @Test
    void createProduct() {
        Product product = new Product(1,"Chair", "Sit On", new BigDecimal("9.99"), new BigDecimal("0.99"));
        Product product2 = new Product("Chair", "Sit On", new BigDecimal("9.99"), new BigDecimal("0.99"));
        doReturn(product).when(dao).createProduct(product2);
        ProductViewModel pvm = new ProductViewModel("Chair", "Sit On", new BigDecimal("9.99"), new BigDecimal("0.99"));
        ProductViewModel pvm2 = new ProductViewModel(1,"Chair", "Sit On", new BigDecimal("9.99"), new BigDecimal("0.99"));
        ProductViewModel fromService = service.createProduct(pvm);
        assertEquals(pvm2, fromService);
    }

    @Test
    void getProduct() {
        Product product = new Product(1,"Chair", "Sit On", new BigDecimal("9.99"), new BigDecimal("0.99"));
        doReturn(product).when(dao).getProduct(1);
        ProductViewModel pvm = new ProductViewModel(1,"Chair", "Sit On", new BigDecimal("9.99"), new BigDecimal("0.99"));
        assertEquals(Optional.of(pvm), service.findProduct(pvm.getProductId()));
    }

    @Test
    void getAllProducts() {
        Product product = new Product(1,"Chair", "Sit On", new BigDecimal("9.99"), new BigDecimal("0.99"));
        List<Product> products = Collections.singletonList(product);
        doReturn(products).when(dao).getAllProducts();
        ProductViewModel pvm = new ProductViewModel(1,"Chair", "Sit On", new BigDecimal("9.99"), new BigDecimal("0.99"));
        List<ProductViewModel> pvms = Collections.singletonList(pvm);
        assertEquals(pvms, service.findAllProducts());
    }

    @Test
    void updateProduct() {
        ProductViewModel pvm = new ProductViewModel(1,"Chair", "Sit On", new BigDecimal("9.99"), new BigDecimal("0.99"));
        Product product = new Product(1,"Chair", "Sit On", new BigDecimal("9.99"), new BigDecimal("0.99"));
        service.updateProduct(pvm);
        verify(dao, times(1)).updateProduct(product);

    }

    @Test
    void deleteProduct() {
        service.deleteProduct(1);
        verify(dao, times(1)).deleteProduct(1);

    }
}