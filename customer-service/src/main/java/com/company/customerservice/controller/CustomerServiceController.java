package com.company.customerservice.controller;

import com.company.customerservice.model.CustomerViewModel;
import com.company.customerservice.service.CustomerServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerServiceController {

    @Autowired
    CustomerServiceLayer service;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerViewModel getCustomer(@PathVariable int id) {

        return service.findCustomer(id).orElseThrow(()-> new HttpClientErrorException(HttpStatus.NOT_FOUND,
                "customer with id " + id + " not found"));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerViewModel> getAllCustomers() {

        return service.findAllCustomers();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerViewModel addCustomer(@RequestBody @Valid CustomerViewModel cvm) {

        return service.createCustomer(cvm);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@RequestBody @Valid CustomerViewModel cvm) {
        service.updateCustomer(cvm);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable int id ) {
        service.deleteCustomer(id);
    }
}
