package com.company.retailapi.controller;

import com.company.retailapi.models.InvoiceViewModel;
import com.company.retailapi.models.ProductViewModel;
import com.company.retailapi.service.ServiceLayer;
import com.company.retailapi.viewmodel.RetailInvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ResponseStatus
@RefreshScope
public class RetailApiController {

    @Autowired
    ServiceLayer serviceLayer;

    @RequestMapping(value = "/invoices", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public RetailInvoiceViewModel submitInvoice(@RequestBody RetailInvoiceViewModel rivm) {
        return serviceLayer.submitInvoice(rivm);
    }

    @RequestMapping(value = "/invoices/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public RetailInvoiceViewModel getInvoiceById(@PathVariable int id) {
        return serviceLayer.getInvoiceById(id);
    }

    @RequestMapping(value = "/invoices", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<RetailInvoiceViewModel> getAllInvoices() {
        return serviceLayer.getAllInvoices();
    }

    @RequestMapping(value = "/invoices/customer/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<RetailInvoiceViewModel> getInvoicesByCustomerId(@PathVariable int id) {
        return serviceLayer.getInvoicesByCustomerId(id);
    }

    @RequestMapping(value = "/products/inventory", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<ProductViewModel> getProductsInInventory() {
        return serviceLayer.getProductsInInventory();
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ProductViewModel getProductById(@PathVariable int id) {
        return serviceLayer.getProductById(id);
    }

    @RequestMapping(value = "/products/invoice/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<ProductViewModel> getProductByInvoiceId(@PathVariable int id) {
        return serviceLayer.getProductByInvoiceId(id);
    }

    @RequestMapping(value = "/levelup/customer/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public int getLevelUpPointsByCustomerId(int id) {
        return serviceLayer.getLevelUpPointsByCustomerId(id);
    }

}
