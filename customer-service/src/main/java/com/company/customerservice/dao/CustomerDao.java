package com.company.customerservice.dao;

import com.company.customerservice.model.Customer;

import java.util.List;

public interface CustomerDao {

    public Customer getCustomer(Integer id);
    public List<Customer> getAllCustomers();
    public Customer createCustomer(Customer customer);
    public void deleteCustomer(Integer id);
    public void updateCustomer(Customer customer);

}
