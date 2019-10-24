package com.company.adminapi.service;

import com.company.adminapi.model.InvoiceViewModelWithItems;
import com.company.adminapi.util.feign.InvoiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServiceLayer {

    @Autowired
    InvoiceClient client;

    public Optional<InvoiceViewModelWithItems> findInvoice(int id) {
        return null;
    }

    public List<InvoiceViewModelWithItems> findAllInvoices() {
        return null;
    }

    public InvoiceViewModelWithItems createInvoice(InvoiceViewModelWithItems ivm) {
        return null;
    }

    public void updateInvoice(InvoiceViewModelWithItems ivm) {

    }

    public void deleteInvoice(int id) {

    }

}
