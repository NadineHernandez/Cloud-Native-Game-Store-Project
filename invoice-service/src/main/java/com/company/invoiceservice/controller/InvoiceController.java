package com.company.invoiceservice.controller;

import com.company.invoiceservice.service.ServiceLayer;
import com.company.invoiceservice.viewmodels.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RefreshScope
@RestController
public class InvoiceController {
    @Autowired
    ServiceLayer serviceLayer;

    @PostMapping(value = "/invoice")
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceViewModel addInvoice(@RequestBody @Valid InvoiceViewModel ivm){
        return null;
    }

    @GetMapping(value = "/invoice")
    @ResponseStatus(HttpStatus.OK)
    public List<InvoiceViewModel> getAllInvoices(){
        return null;
    }

    @GetMapping(value = "/invoice/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InvoiceViewModel getInvoice(@PathVariable int id){
        return null;
    }

    @PutMapping(value = "/invoice")
    @ResponseStatus(HttpStatus.OK)
    public void updateInvoice(@RequestBody @Valid InvoiceViewModel ivm){

    }

    @DeleteMapping(value = "/invoice/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteInvoice(@PathVariable int id){

    }
}
