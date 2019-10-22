package com.company.productservice.controller;

import com.company.productservice.model.ProductViewModel;
import com.company.productservice.service.ProductServiceLayer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ProductServiceController.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
class ProductServiceControllerTest {

    @MockBean
    ProductServiceLayer service;

    @Autowired
    MockMvc mockMvc;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    void getProduct() throws Exception{
        ProductViewModel pvm = new ProductViewModel(1,"Chair", "Sit On", new BigDecimal("9.99"), new BigDecimal("0.99"));
        when(service.findProduct(pvm.getProductId())).thenReturn(Optional.of(pvm));
        String outputJson = mapper.writeValueAsString(pvm);
        mockMvc.perform(get("/product/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

        mockMvc.perform(get("/product/9999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllProducts() throws Exception{
        ProductViewModel pvm = new ProductViewModel(1,"Chair", "Sit On", new BigDecimal("9.99"), new BigDecimal("0.99"));
        List<ProductViewModel> pvms = Collections.singletonList(pvm);
        when(service.findAllProducts()).thenReturn(pvms);
        String outputJson = mapper.writeValueAsString(pvms);
        mockMvc.perform(get("/product"))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    void addProduct() throws Exception{
        ProductViewModel pvm = new ProductViewModel(1,"Chair", "Sit On", new BigDecimal("9.99"), new BigDecimal("0.99"));
        ProductViewModel pvm2 = new ProductViewModel("Chair", "Sit On", new BigDecimal("9.99"), new BigDecimal("0.99"));
        String inputJson = mapper.writeValueAsString(pvm2);
        String outputJson =  mapper.writeValueAsString(pvm);
        when(service.createProduct(pvm2)).thenReturn(pvm);
        mockMvc.perform(post("/product")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));


    }

    @Test
    void updateProduct() {
    }

    @Test
    void deleteProduct() {
    }
}