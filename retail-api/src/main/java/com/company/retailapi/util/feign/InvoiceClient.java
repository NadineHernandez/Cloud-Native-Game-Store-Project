package com.company.retailapi.util.feign;

import com.company.retailapi.models.InvoiceItemViewModel;
import com.company.retailapi.models.InvoiceViewModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "invoice-service")
public interface InvoiceClient {
    @PostMapping(value = "/invoice")
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceViewModel addInvoice(@RequestBody @Valid InvoiceViewModel ivm);

    @GetMapping(value = "/invoice")
    @ResponseStatus(HttpStatus.OK)
    public List<InvoiceViewModel> getAllInvoices();

    @GetMapping(value = "/invoice/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InvoiceViewModel getInvoice(@PathVariable int id);

    @PutMapping(value = "/invoice")
    @ResponseStatus(HttpStatus.OK)
    public void updateInvoice(@RequestBody @Valid InvoiceViewModel ivm);

    @DeleteMapping(value = "/invoice/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteInvoice(@PathVariable int id);

    @GetMapping(value = "/invoice/customer/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public List<InvoiceViewModel> getInvoicesByCustomerId(@PathVariable Integer customerId);

    @PostMapping(value = "/invoiceitem")
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceItemViewModel addInvoiceItem(@RequestBody @Valid InvoiceItemViewModel iivm);

    @GetMapping(value = "/invoiceitem")
    @ResponseStatus(HttpStatus.OK)
    public List<InvoiceItemViewModel> getAllInvoiceItems();

    @GetMapping(value = "/invoiceitem/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InvoiceItemViewModel getInvoiceItem(@PathVariable int id);

    @GetMapping(value = "/invoiceitem/invoice/{invoiceId}")
    public List<InvoiceItemViewModel> getInvoiceItemsByInvoiceId(@PathVariable int invoiceId);

    @PutMapping(value = "/invoiceitem")
    @ResponseStatus(HttpStatus.OK)
    public void updateInvoiceItem(@RequestBody @Valid InvoiceItemViewModel iivm);

    @DeleteMapping(value = "/invoiceitem/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteInvoiceItem(@PathVariable int id);
}
