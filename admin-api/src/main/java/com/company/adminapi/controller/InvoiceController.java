package com.company.adminapi.controller;


import com.company.adminapi.model.InvoiceItemViewModel;
import com.company.adminapi.model.InvoiceViewModel;
import com.company.adminapi.service.InvoiceServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import java.util.List;

@RestController
public class InvoiceController {

    @Autowired
    InvoiceServiceLayer service;

    @GetMapping("/invoice/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InvoiceViewModel getInvoice(@PathVariable int id) {
        return service.findInvoice(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/invoice")
    @ResponseStatus(HttpStatus.OK)
    public List<InvoiceViewModel> getAllInvoices() {
        return service.findAllInvoices();
    }

    @PostMapping("/invoice")
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceViewModel addInvoice(@RequestBody @Valid InvoiceViewModel ivm) {
        return service.createInvoice(ivm);
    }

    @PutMapping("/invoice")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateInvoice(@RequestBody @Valid InvoiceViewModel ivm) {
        service.updateInvoice(ivm);
    }

    @DeleteMapping("/invoice/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoice(@PathVariable int id) {
        service.deleteInvoice(id);
    }

    @GetMapping("/invoiceitem/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InvoiceItemViewModel getInvoiceItem(@PathVariable int id) {
        return service.findInvoiceItem(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/invoiceitem")
    @ResponseStatus(HttpStatus.OK)
    public List<InvoiceItemViewModel> getAllInvoiceItems() {
        return service.findAllInvoiceItems();
    }

    @GetMapping("/invoiceitem/invoice/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<InvoiceItemViewModel> getInvoiceItemsByInvoiceId(@PathVariable int id) {
        return service.findInvoiceItemsByInvoiceId(id);
    }

    @PostMapping("/invoiceitem")
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceItemViewModel addInvoiceItem(@RequestBody @Valid InvoiceItemViewModel iivm) {
        return service.createInvoiceItem(iivm);
    }

    @PutMapping("/invoiceitem")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateInvoiceItem(@RequestBody @Valid InvoiceItemViewModel iivm) {
        service.updateInvoiceItem(iivm);
    }

    @DeleteMapping("/invoiceitem/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoiceItem(@PathVariable int id ) {
        service.deleteInvoiceItem(id);
    }




}
