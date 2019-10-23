package com.company.customerservice.dao;

import com.company.customerservice.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class CustomerDaoTest {

    @Autowired
    CustomerDao dao;

    @BeforeEach
    void setUp() {
        dao.getAllCustomers().forEach(customer -> dao.deleteCustomer(customer.getCustomerId()));
    }

    @Test
    void getCustomer() {
        Customer customer = new Customer("John", "Doe", "123 Abc Street", "Atlanta","11111", "john@doe.com", "555-5555");
        customer = dao.createCustomer(customer);
        assertEquals(customer, dao.getCustomer(customer.getCustomerId()));
    }

    @Test
    void getAllCustomers() {
        Customer customer = new Customer("John", "Doe", "123 Abc Street", "Atlanta", "11111", "john@doe.com", "555-5555");
        customer = dao.createCustomer(customer);
        List<Customer> customers = Collections.singletonList(customer);
        assertEquals(customers, dao.getAllCustomers());
    }

    @Test
    void createCustomer() {
        Customer customer = new Customer("John", "Doe", "123 Abc Street", "Atlanta", "11111", "john@doe.com", "555-5555");
        customer = dao.createCustomer(customer);
        assertEquals(customer, dao.getCustomer(customer.getCustomerId()));
    }

    @Test
    void deleteCustomer() {
        Customer customer = new Customer("John", "Doe", "123 Abc Street", "Atlanta", "11111", "john@doe.com", "555-5555");
        customer = dao.createCustomer(customer);
        dao.deleteCustomer(customer.getCustomerId());
        assertNull(dao.getCustomer(customer.getCustomerId()));
    }

    @Test
    void updateCustomer() {
        Customer customer = new Customer("John", "Doe", "123 Abc Street", "Atlanta", "11111", "john@doe.com", "555-5555");
        customer = dao.createCustomer(customer);
        customer.setFirstName("Jonathan");
        dao.updateCustomer(customer);
        assertEquals(customer, dao.getCustomer(customer.getCustomerId()));
    }
}