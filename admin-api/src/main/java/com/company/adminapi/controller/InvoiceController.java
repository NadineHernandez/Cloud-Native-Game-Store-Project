package com.company.adminapi.controller;


import com.company.adminapi.model.InvoiceViewModelWithItems;
import com.company.adminapi.service.InvoiceServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    InvoiceServiceLayer service;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InvoiceViewModelWithItems getInvoice(@PathVariable int id) {
        return service.findInvoice(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InvoiceViewModelWithItems> getAllInvoices() {
        return service.findAllInvoices();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceViewModelWithItems addInvoice(@RequestBody @Valid InvoiceViewModelWithItems ivm) {
        return service.createInvoice(ivm);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateInvoice(@RequestBody @Valid InvoiceViewModelWithItems ivm) {
        service.updateInvoice(ivm);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoice(@PathVariable int id) {
        service.deleteInvoice(id);
    }


}
