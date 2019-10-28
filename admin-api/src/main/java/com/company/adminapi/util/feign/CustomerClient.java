package com.company.adminapi.util.feign;

import com.company.adminapi.model.CustomerViewModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FeignClient(name="customer-service", decode404=true)
public interface CustomerClient {

    @GetMapping("/customer/{id}")
//    @ResponseStatus(HttpStatus.OK)
    public Optional<CustomerViewModel> getCustomer(@PathVariable int id);

    @GetMapping("/customer")
//    @ResponseStatus(HttpStatus.OK)
    public List<CustomerViewModel> getAllCustomers();

    @PostMapping("/customer")
//    @ResponseStatus(HttpStatus.CREATED)
    public CustomerViewModel addCustomer(@RequestBody CustomerViewModel cvm);

    @PutMapping("/customer")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@RequestBody CustomerViewModel cvm);

    @DeleteMapping("/customer/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable int id);

}
