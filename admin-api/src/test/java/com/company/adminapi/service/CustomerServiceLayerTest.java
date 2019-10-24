package com.company.adminapi.service;

import com.company.adminapi.util.feign.CustomerClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CustomerServiceLayerTest {

    @InjectMocks
    CustomerServiceLayer service;

    @Mock
    CustomerClient client;

    @Test
    void findCustomer() {
    }

    @Test
    void findAllCustomers() {
    }

    @Test
    void createCustomer() {
    }

    @Test
    void updateCustomer() {
    }

    @Test
    void deleteCustomer() {
    }
}