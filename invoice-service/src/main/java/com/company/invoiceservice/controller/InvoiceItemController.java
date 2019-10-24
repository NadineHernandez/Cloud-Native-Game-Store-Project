package com.company.invoiceservice.controller;

import com.company.invoiceservice.service.ServiceLayer;
import com.company.invoiceservice.viewmodels.InvoiceItemViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
        return serviceLayer.createInvoiceItem(iivm);
    }

    @GetMapping(value = "/invoiceitem")
    @ResponseStatus(HttpStatus.OK)
    public List<InvoiceItemViewModel> getAllInvoiceItems(){
        return serviceLayer.findAllInvoiceItems();
    }

    @GetMapping(value = "/invoiceitem/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InvoiceItemViewModel getInvoiceItem(@PathVariable int id){
        try{
            int tester = serviceLayer.findInvoiceItem(id).getInvoice_item_id();
        } catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No Invoice Items found with id: " + id, e
            );
        }
        return serviceLayer.findInvoiceItem(id);
    }

    @GetMapping(value = "/invoiceitem/invoice/{invoiceId}")
    public List<InvoiceItemViewModel> getInvoiceItemsByInvoiceId(@PathVariable int invoiceId){
        try{
            int tester = serviceLayer.findInvoice(invoiceId).getInvoice_id();
        } catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No Invoices found matching id: " + invoiceId, e
            );
        }
        return serviceLayer.findInvoiceItemsByInvoiceId(invoiceId);
    }

    @PutMapping(value = "/invoiceitem")
    @ResponseStatus(HttpStatus.OK)
    public void updateInvoiceItem(@RequestBody @Valid InvoiceItemViewModel iivm){
        serviceLayer.updateInvoiceItem(iivm);
    }

    @DeleteMapping(value = "/invoiceitem/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteInvoiceItem(@PathVariable int id){
        serviceLayer.deleteInvoice(id);
    }
}
