package com.company.adminapi.service;

import com.company.adminapi.model.ProductViewModel;
import com.company.adminapi.util.feign.ProductClient;
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
    ProductClient client;

    @Test
    void findProduct() {
        ProductViewModel pvm = new ProductViewModel(1,"Chair", "Sit On", new BigDecimal("9.99"), new BigDecimal("0.99"));
        when(client.getProduct(pvm.getProductId())).thenReturn(Optional.of(pvm));
        assertEquals(Optional.of(pvm), service.findProduct(pvm.getProductId()));
    }

    @Test
    void findAllProducts() {
        ProductViewModel pvm = new ProductViewModel(1,"Chair", "Sit On", new BigDecimal("9.99"), new BigDecimal("0.99"));
        List<ProductViewModel> pvms = Collections.singletonList(pvm);
        when(client.getAllProducts()).thenReturn(pvms);
        assertEquals(pvms, service.findAllProducts());
    }

    @Test
    void createProduct() {
        ProductViewModel pvm = new ProductViewModel(1,"Chair", "Sit On", new BigDecimal("9.99"), new BigDecimal("0.99"));
        ProductViewModel pvm2 = new ProductViewModel("Chair", "Sit On", new BigDecimal("9.99"), new BigDecimal("0.99"));
        when(client.addProduct(pvm2)).thenReturn(pvm);
        ProductViewModel fromService = service.createProduct(pvm2);
        assertEquals(pvm, fromService);
    }

    @Test
    void updateProduct() {
        ProductViewModel pvm = new ProductViewModel(1,"Chair", "Sit On", new BigDecimal("9.99"), new BigDecimal("0.99"));
        service.updateProduct(pvm);
        verify(client, times(1)).updateProduct(pvm);
    }

    @Test
    void deleteProduct() {
        ProductViewModel pvm = new ProductViewModel(1,"Chair", "Sit On", new BigDecimal("9.99"), new BigDecimal("0.99"));
        service.deleteProduct(1);
        verify(client, times(1)).deleteProduct(1);
    }
}