package com.company.adminapi.controller;

import com.company.adminapi.model.ProductViewModel;
import com.company.adminapi.service.ProductServiceLayer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
class ProductControllerTest {

    @MockBean
    DataSource dataSource;

    @MockBean
    ProductServiceLayer service;

    @Autowired
    MockMvc mockMvc;

    ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
    }

    @Test
    @WithMockUser(username="adminUser", roles = {"ADMIN"})
    void getProduct() throws Exception {
        ProductViewModel pvm = new ProductViewModel(1,"Chair", "Sit On", new BigDecimal("9.99"), new BigDecimal("0.99"));
        when(service.findProduct(pvm.getProductId())).thenReturn(Optional.of(pvm));
        String outputJson = mapper.writeValueAsString(pvm);
        mockMvc.perform(get("/product/1")
                .with(csrf().asHeader()))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    @WithMockUser(username="adminUser", roles = {"ADMIN"})
    void getAllProducts() throws Exception{
        ProductViewModel pvm = new ProductViewModel(1,"Chair", "Sit On", new BigDecimal("9.99"), new BigDecimal("0.99"));
        List<ProductViewModel> pvms = Collections.singletonList(pvm);
        when(service.findAllProducts()).thenReturn(pvms);
        String outputJson = mapper.writeValueAsString(pvms);
        mockMvc.perform(get("/product")
                .with(csrf().asHeader()))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    @WithMockUser(username="adminUser", roles = {"ADMIN"})
    void addProduct() throws Exception{
        ProductViewModel pvm = new ProductViewModel(1,"Chair", "Sit On", new BigDecimal("9.99"), new BigDecimal("0.99"));
        ProductViewModel pvm2 = new ProductViewModel("Chair", "Sit On", new BigDecimal("9.99"), new BigDecimal("0.99"));
        when(service.createProduct(pvm2)).thenReturn(pvm);
        String inputJson = mapper.writeValueAsString(pvm2);
        String outputJson = mapper.writeValueAsString(pvm);
        mockMvc.perform(post("/product")
                .with(csrf().asHeader())
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    @WithMockUser(username="adminUser", roles = {"ADMIN"})
    void updateProduct() throws Exception {
        ProductViewModel pvm = new ProductViewModel(1,"Chair", "Sit On", new BigDecimal("9.99"), new BigDecimal("0.99"));
        String inputJson = mapper.writeValueAsString(pvm);
        mockMvc.perform(put("/product")
                .with(csrf().asHeader())
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(username="adminUser", roles = {"ADMIN"})
    void deleteProduct() throws Exception{

        mockMvc.perform(delete("/product/1")
                .with(csrf().asHeader()))
                .andExpect(status().isNoContent());
    }

    //copies of tests with no auth

    @Test
    void getProductNoAuth() throws Exception {
        ProductViewModel pvm = new ProductViewModel(1, "Chair", "Sit On", new BigDecimal("9.99"), new BigDecimal("0.99"));
        when(service.findProduct(pvm.getProductId())).thenReturn(Optional.of(pvm));
        String outputJson = mapper.writeValueAsString(pvm);
        mockMvc.perform(get("/product/1"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void getAllProductsNoAuth() throws Exception{
        ProductViewModel pvm = new ProductViewModel(1,"Chair", "Sit On", new BigDecimal("9.99"), new BigDecimal("0.99"));
        List<ProductViewModel> pvms = Collections.singletonList(pvm);
        when(service.findAllProducts()).thenReturn(pvms);
        String outputJson = mapper.writeValueAsString(pvms);
        mockMvc.perform(get("/product"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void addProductNoAuth() throws Exception{
        ProductViewModel pvm = new ProductViewModel(1,"Chair", "Sit On", new BigDecimal("9.99"), new BigDecimal("0.99"));
        ProductViewModel pvm2 = new ProductViewModel("Chair", "Sit On", new BigDecimal("9.99"), new BigDecimal("0.99"));
        when(service.createProduct(pvm2)).thenReturn(pvm);
        String inputJson = mapper.writeValueAsString(pvm2);
        String outputJson = mapper.writeValueAsString(pvm);
        mockMvc.perform(post("/product")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    void updateProductNoAuth() throws Exception {
        ProductViewModel pvm = new ProductViewModel(1,"Chair", "Sit On", new BigDecimal("9.99"), new BigDecimal("0.99"));
        String inputJson = mapper.writeValueAsString(pvm);
        mockMvc.perform(put("/product")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    void deleteProductNoAuth() throws Exception{

        mockMvc.perform(delete("/product/1"))
                .andExpect(status().isForbidden());
    }
}