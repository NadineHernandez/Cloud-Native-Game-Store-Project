package com.company.invoiceservice.controller;

import com.company.invoiceservice.service.ServiceLayer;
import com.company.invoiceservice.viewmodels.InvoiceItemViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RefreshScope
@RestController
public class InvoiceItemController {
    @Autowired
    ServiceLayer serviceLayer;

    @PostMapping(value = "/invoiceitem")
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceItemViewModel addInvoiceItem(@RequestBody @Valid InvoiceItemViewModel iivm){
        return null;
    }

    @GetMapping(value = "/invoiceitem")
    @ResponseStatus(HttpStatus.OK)
    public List<InvoiceItemViewModel> getAllInvoiceItems(){
        return null;
    }

    @GetMapping(value = "/invoiceitem/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InvoiceItemViewModel getInvoiceItem(@PathVariable int id){
        return null;
    }

    @GetMapping(value = "invoiceitem/invoice/{invoiceId}")
    public List<InvoiceItemViewModel> getInvoiceItemsByInvoiceId(@PathVariable int invoiceId){
        return null;
    }

    @PutMapping(value = "/invoiceitem")
    @ResponseStatus(HttpStatus.OK)
    public void updateInvoiceItem(@RequestBody @Valid InvoiceItemViewModel iivm){

    }

    @DeleteMapping(value = "/invoiceitem/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteInvoiceItem(@PathVariable int id){

    }
}
