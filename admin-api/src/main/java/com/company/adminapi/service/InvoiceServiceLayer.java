package com.company.adminapi.service;

import com.company.adminapi.model.InvoiceItemViewModel;
import com.company.adminapi.model.InvoiceViewModel;
import com.company.adminapi.util.feign.InvoiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServiceLayer {

    @Autowired
    InvoiceClient client;

    public Optional<InvoiceViewModel> findInvoice(int id) {

        return client.getInvoice(id);
    }

    public List<InvoiceViewModel> findAllInvoices() {

        return client.getAllInvoices();
    }

    public InvoiceViewModel createInvoice(InvoiceViewModel ivm) {

        return client.addInvoice(ivm);
    }

    public void updateInvoice(InvoiceViewModel ivm) {
        client.updateInvoice(ivm);
    }

    public void deleteInvoice(int id) {
        client.deleteInvoice(id);
    }

    public Optional<InvoiceItemViewModel> findInvoiceItem(int id) {

        return client.getInvoiceItem(id);
    }

    public List<InvoiceItemViewModel> findAllInvoiceItems() {

        return client.getAllInvoiceItems();
    }

    public InvoiceItemViewModel createInvoiceItem(InvoiceItemViewModel iivm) {

        return client.addInvoiceItem(iivm);
    }

    public List<InvoiceItemViewModel> findInvoiceItemsByInvoiceId(int id) {

        return client.getInvoiceItemsByInvoiceId(id);
    }

    public void updateInvoiceItem(InvoiceItemViewModel iivm) {

        client.updateInvoiceItem(iivm);

    }

    public void deleteInvoiceItem(int id) {
        client.deleteInvoiceItem(id);
    }

}
