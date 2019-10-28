package com.company.adminapi.controller;

import com.company.adminapi.model.CustomerViewModel;
import com.company.adminapi.service.CustomerServiceLayer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerController.class)

class CustomerControllerTest {


    @MockBean
    DataSource dataSource;

    @MockBean
    CustomerServiceLayer service;

    @Autowired
    MockMvc mockMvc;


    ObjectMapper mapper = new ObjectMapper();

    @Test
    @WithMockUser(username="adminUser", roles = {"ADMIN"})
    void getCustomer() throws Exception {
        CustomerViewModel cvm = new CustomerViewModel(101,"John", "Doe", "123 Abc Street", "Atlanta", "11111", "john@doe.com", "555-5555");
        when(service.findCustomer(cvm.getCustomerId())).thenReturn(Optional.of(cvm));
        String outputJson = mapper.writeValueAsString(cvm);
        mockMvc.perform(get("/customer/101")
                .with(csrf().asHeader()))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

        mockMvc.perform(get("/customer/9999")
                .with(csrf().asHeader()))
                .andExpect(status().isNotFound());

    }

    @Test
    @WithMockUser(username="adminUser", roles = {"ADMIN"})
    void getAllCustomers() throws Exception {
        CustomerViewModel cvm = new CustomerViewModel(101,"John", "Doe", "123 Abc Street", "Atlanta", "11111", "john@doe.com", "555-5555");
        List<CustomerViewModel> cvms = Collections.singletonList(cvm);
        when(service.findAllCustomers()).thenReturn(cvms);
        String outputJson = mapper.writeValueAsString(cvms);
        mockMvc.perform(get("/customer")
                .with(csrf().asHeader()))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    @WithMockUser(username="adminUser", roles = {"ADMIN"})
    void addCustomer() throws Exception {
        CustomerViewModel cvm = new CustomerViewModel(101,"John", "Doe", "123 Abc Street", "Atlanta", "11111", "john@doe.com", "555-5555");
        CustomerViewModel cvm2 = new CustomerViewModel("John", "Doe", "123 Abc Street", "Atlanta", "11111", "john@doe.com", "555-5555");
        String inputJson = mapper.writeValueAsString(cvm2);
        String outputJson = mapper.writeValueAsString(cvm);
        when(service.createCustomer(cvm2)).thenReturn(cvm);
        mockMvc.perform(post("/customer")
                .with(csrf().asHeader())
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    @WithMockUser(username="adminUser", roles = {"ADMIN"})
    void updateCustomer() throws Exception {
        CustomerViewModel cvm = new CustomerViewModel(101,"John", "Doe", "123 Abc Street", "Atlanta", "11111", "john@doe.com", "555-5555");
        String inputJson = mapper.writeValueAsString(cvm);
        mockMvc.perform(put("/customer")
                .with(csrf().asHeader())
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(username="adminUser", roles = {"ADMIN"})
    void deleteCustomer() throws Exception {
        mockMvc.perform(delete("/customer/101")
                .with(csrf().asHeader()))
                .andExpect(status().isNoContent());
    }

    //Copies of tests without Authority

    @Test
    void getCustomerNoAuth() throws Exception {
        CustomerViewModel cvm = new CustomerViewModel(101,"John", "Doe", "123 Abc Street", "Atlanta", "11111", "john@doe.com", "555-5555");
        when(service.findCustomer(cvm.getCustomerId())).thenReturn(Optional.of(cvm));
        String outputJson = mapper.writeValueAsString(cvm);
        mockMvc.perform(get("/customer/101"))
                .andExpect(status().isUnauthorized());

        mockMvc.perform(get("/customer/9999"))
                .andExpect(status().isUnauthorized());

    }

    @Test
    void getAllCustomersNoAuth() throws Exception {
        CustomerViewModel cvm = new CustomerViewModel(101,"John", "Doe", "123 Abc Street", "Atlanta", "11111", "john@doe.com", "555-5555");
        List<CustomerViewModel> cvms = Collections.singletonList(cvm);
        when(service.findAllCustomers()).thenReturn(cvms);
        String outputJson = mapper.writeValueAsString(cvms);
        mockMvc.perform(get("/customer"))
                .andExpect(status().isUnauthorized());
    }

    @Test

    void addCustomerNoAuth() throws Exception {
        CustomerViewModel cvm = new CustomerViewModel(101,"John", "Doe", "123 Abc Street", "Atlanta", "11111", "john@doe.com", "555-5555");
        CustomerViewModel cvm2 = new CustomerViewModel("John", "Doe", "123 Abc Street", "Atlanta", "11111", "john@doe.com", "555-5555");
        String inputJson = mapper.writeValueAsString(cvm2);
        String outputJson = mapper.writeValueAsString(cvm);
        when(service.createCustomer(cvm2)).thenReturn(cvm);
        mockMvc.perform(post("/customer")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    void updateCustomerNoAuth() throws Exception {
        CustomerViewModel cvm = new CustomerViewModel(101, "John", "Doe", "123 Abc Street", "Atlanta", "11111", "john@doe.com", "555-5555");
        String inputJson = mapper.writeValueAsString(cvm);
        mockMvc.perform(put("/customer")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    void deleteCustomerNoAuth() throws Exception {
        mockMvc.perform(delete("/customer/101"))
                .andExpect(status().isForbidden());
    }
}

