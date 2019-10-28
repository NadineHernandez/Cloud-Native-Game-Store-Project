package com.company.retailapi.controller;

import com.company.retailapi.models.InvoiceViewModel;
import com.company.retailapi.models.ProductViewModel;
import com.company.retailapi.service.ServiceLayer;
import com.company.retailapi.viewmodel.RetailInvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CacheConfig(cacheNames = {"game-store"})
@RefreshScope
public class RetailApiController {

    @Autowired
    ServiceLayer serviceLayer;

    @CachePut(key = "#result.getInvoiceId()")
    @PostMapping(value = "/invoices")
    @ResponseStatus(HttpStatus.CREATED)
    public RetailInvoiceViewModel submitInvoice(@RequestBody RetailInvoiceViewModel rivm) {
        return serviceLayer.submitInvoice(rivm);
    }

    @Cacheable
    @GetMapping(value = "/invoices/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RetailInvoiceViewModel getInvoiceById(@PathVariable int id) {
        try {int tester = serviceLayer.getInvoiceById(id).getInvoiceId();
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Invoice cannot be found with id: " + id, e);
        }
        return serviceLayer.getInvoiceById(id);
    }

    @GetMapping(value = "/invoices")
    @ResponseStatus(HttpStatus.OK)
    public List<RetailInvoiceViewModel> getAllInvoices() {
        return serviceLayer.getAllInvoices();
    }

    @GetMapping(value = "/invoices/customer/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<RetailInvoiceViewModel> getInvoicesByCustomerId(@PathVariable int id) {
        return serviceLayer.getInvoicesByCustomerId(id);
    }

    @GetMapping(value = "/products/inventory")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductViewModel> getProductsInInventory() {
        return serviceLayer.getProductsInInventory();
    }

    @Cacheable
    @GetMapping(value = "/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductViewModel getProductById(@PathVariable int id) {
        return serviceLayer.getProductById(id);
    }

    @GetMapping(value = "/products/invoice/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductViewModel> getProductByInvoiceId(@PathVariable int id) {
        return serviceLayer.getProductByInvoiceId(id);
    }

    @GetMapping(value = "/levelup/customer/{id}")
    @ResponseStatus(HttpStatus.OK)
    public int getLevelUpPointsByCustomerId(int id) {
        return serviceLayer.getLevelUpPointsByCustomerId(id);
    }

}
