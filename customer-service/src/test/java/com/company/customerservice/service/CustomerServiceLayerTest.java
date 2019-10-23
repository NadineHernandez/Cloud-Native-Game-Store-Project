package com.company.customerservice.service;

import com.company.customerservice.dao.CustomerDao;
import com.company.customerservice.model.Customer;
import com.company.customerservice.model.CustomerViewModel;
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
    CustomerDao dao;

    @Test
    void createCustomer() {
        Customer customer = new Customer(101,"John", "Doe", "123 Abc Street", "Atlanta", "11111", "john@doe.com", "555-5555");
        Customer customer2 = new Customer("John", "Doe", "123 Abc Street", "Atlanta", "11111", "john@doe.com", "555-5555");
        doReturn(customer).when(dao).createCustomer(customer2);
        CustomerViewModel cvm = new CustomerViewModel("John", "Doe", "123 Abc Street", "Atlanta", "11111", "john@doe.com", "555-5555");
        CustomerViewModel cvm2 = new CustomerViewModel(101,"John", "Doe", "123 Abc Street", "Atlanta", "11111", "john@doe.com", "555-5555");
        CustomerViewModel fromService = service.createCustomer(cvm);
        assertEquals(cvm2, fromService);

    }

    @Test
    void findCustomer() {
        Customer customer = new Customer(101,"John", "Doe", "123 Abc Street", "Atlanta", "11111", "john@doe.com", "555-5555");
        doReturn(customer).when(dao).getCustomer(101);
        CustomerViewModel cvm = new CustomerViewModel(101,"John", "Doe", "123 Abc Street", "Atlanta", "11111", "john@doe.com", "555-5555");
        assertEquals(Optional.of(cvm), service.findCustomer(cvm.getCustomerId()));
    }

    @Test
    void findAllCustomers() {
        Customer customer = new Customer(101,"John", "Doe", "123 Abc Street", "Atlanta", "11111", "john@doe.com", "555-5555");
        List<Customer> customers = Collections.singletonList(customer);
        doReturn(customers).when(dao).getAllCustomers();
        CustomerViewModel cvm = new CustomerViewModel(101,"John", "Doe", "123 Abc Street", "Atlanta", "11111", "john@doe.com", "555-5555");
        List<CustomerViewModel> cvms = Collections.singletonList(cvm);
        assertEquals(cvms, service.findAllCustomers());
    }

    @Test
    void updateCustomer() {
        CustomerViewModel cvm = new CustomerViewModel(101,"John", "Doe", "123 Abc Street", "Atlanta", "11111", "john@doe.com", "555-5555");
        Customer customer = new Customer(101,"John", "Doe", "123 Abc Street", "Atlanta", "11111", "john@doe.com", "555-5555");
        service.updateCustomer(cvm);
        verify(dao, times(1)).updateCustomer(customer);
    }

    @Test
    void deleteCustomer() {
        service.deleteCustomer(1);
        verify(dao, times(1)).deleteCustomer(1);
    }
}