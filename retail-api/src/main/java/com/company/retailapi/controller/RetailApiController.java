package com.company.retailapi.controller;

import com.company.retailapi.models.InvoiceViewModel;
import com.company.retailapi.models.ProductViewModel;
import com.company.retailapi.viewmodel.RetailInvoiceViewModel;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ResponseStatus
@RefreshScope
public class RetailApiController {
    @RequestMapping(value = "/invoices", method = RequestMethod.POST)
    public RetailInvoiceViewModel submitInvoice(@RequestBody RetailInvoiceViewModel rivm) {
        return null;
    }

    @RequestMapping(value = "/invoices/{id}", method = RequestMethod.GET)
    public RetailInvoiceViewModel getInvoiceById(@PathVariable int id) {
        return null;
    }

    @RequestMapping(value = "/invoices", method = RequestMethod.GET)
    public List<RetailInvoiceViewModel> getAllInvoices() {
        return null;
    }

    @RequestMapping(value = "/invoices/customer/{id}", method = RequestMethod.GET)
    public List<RetailInvoiceViewModel> getInvoicesByCustomerId(@PathVariable int id) {
        return null;
    }

    @RequestMapping(value = "/products/inventory", method = RequestMethod.GET)
    public List<ProductViewModel> getProductsInInventory() {
        return null;
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public ProductViewModel getProductById(@PathVariable int id) {
        return null;
    }

    @RequestMapping(value = "/products/invoice/{id}", method = RequestMethod.GET)
    public List<ProductViewModel> getProductByInvoiceId(@PathVariable int id) {
        return null;
    }

    @RequestMapping(value = "/levelup/customer/{id}", method = RequestMethod.GET)
    public int getLevelUpPointsByCustomerId(int id) {
        return 0;
    }

}
