package com.company.adminapi.service;

import com.company.adminapi.model.CustomerViewModel;
import com.company.adminapi.util.feign.CustomerClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceLayerTest {

    @InjectMocks
    CustomerServiceLayer service;

    @Mock
    CustomerClient client;

    @Test
    void findCustomer() {
        CustomerViewModel cvm = new CustomerViewModel(101,"John", "Doe", "123 Abc Street", "Atlanta", "11111", "john@doe.com", "555-5555");
        when(client.getCustomer(cvm.getCustomerId())).thenReturn(Optional.of(cvm));
        assertEquals(cvm, service.findCustomer(cvm.getCustomerId()));

    }

    @Test
    void findAllCustomers() {
        CustomerViewModel cvm = new CustomerViewModel(101,"John", "Doe", "123 Abc Street", "Atlanta", "11111", "john@doe.com", "555-5555");
        List<CustomerViewModel> cvms = Collections.singletonList(cvm);
        when(client.getAllCustomers()).thenReturn(cvms);
        assertEquals(cvms, service.findAllCustomers());

    }

    @Test
    void createCustomer() {
        CustomerViewModel cvm = new CustomerViewModel(101,"John", "Doe", "123 Abc Street", "Atlanta", "11111", "john@doe.com", "555-5555");
        CustomerViewModel cvm2 = new CustomerViewModel("John", "Doe", "123 Abc Street", "Atlanta", "11111", "john@doe.com", "555-5555");
        when(client.addCustomer(cvm2)).thenReturn(cvm);
        CustomerViewModel fromService = service.createCustomer(cvm2);
        assertEquals(cvm, fromService);
    }

    @Test
    void updateCustomer() {
        CustomerViewModel cvm = new CustomerViewModel(101,"John", "Doe", "123 Abc Street", "Atlanta", "11111", "john@doe.com", "555-5555");
        service.updateCustomer(cvm);
        verify(client, times(1)).updateCustomer(cvm);
    }

    @Test
    void deleteCustomer() {
        service.deleteCustomer(101);
        verify(client, times(1)).deleteCustomer(101);
    }
}