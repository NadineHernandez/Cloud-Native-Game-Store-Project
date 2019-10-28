package com.company.adminapi.service;

import com.company.adminapi.model.CustomerViewModel;
import com.company.adminapi.util.feign.CustomerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceLayer {

    @Autowired
    CustomerClient client;

    public Optional<CustomerViewModel> findCustomer(int id) {

        return client.getCustomer(id);
    }

    public List<CustomerViewModel> findAllCustomers() {

        return client.getAllCustomers();
    }

    public CustomerViewModel createCustomer(CustomerViewModel cvm) {

        return client.addCustomer(cvm);
    }

    public void updateCustomer(CustomerViewModel cvm) {
        client.updateCustomer(cvm);
    }

    public void deleteCustomer(int id) {
        client.deleteCustomer(id);
    }

}
