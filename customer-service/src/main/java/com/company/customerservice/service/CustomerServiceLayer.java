package com.company.customerservice.service;

import com.company.customerservice.dao.CustomerDao;
import com.company.customerservice.model.Customer;
import com.company.customerservice.model.CustomerViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceLayer {

    @Autowired
    CustomerDao dao;

    public CustomerViewModel createCustomer(CustomerViewModel cvm) {

        return buildCvm(dao.createCustomer(buildCustomer(cvm)));
    }

    private Customer buildCustomer(CustomerViewModel cvm) {
        return new Customer(cvm.getCustomerId(),
                cvm.getFirstName(),
                cvm.getLastName(),
                cvm.getStreet(),
                cvm.getCity(),
                cvm.getZip(),
                cvm.getEmail(),
                cvm.getPhone());
    }

    private CustomerViewModel buildCvm(Customer customer) {

        return new CustomerViewModel(customer.getCustomerId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getStreet(),
                customer.getCity(),
                customer.getZip(),
                customer.getEmail(),
                customer.getPhone());
    }

    public Optional<CustomerViewModel> findCustomer(Integer id) {

        return Optional.ofNullable(dao.getCustomer(id)).map(this::buildCvm);
    }

    public List<CustomerViewModel> findAllCustomers() {

        return dao.getAllCustomers().stream().map(this::buildCvm).collect(Collectors.toList());
    }

    public void updateCustomer(CustomerViewModel cvm) {
        dao.updateCustomer(buildCustomer(cvm));
    }

    public void deleteCustomer(Integer id) {
        dao.deleteCustomer(id);
    }


}
