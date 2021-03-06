package com.company.invoiceservice.service;

import com.company.invoiceservice.dao.InvoiceDao;
import com.company.invoiceservice.dao.InvoiceItemDao;
import com.company.invoiceservice.dto.Invoice;
import com.company.invoiceservice.dto.InvoiceItem;
import com.company.invoiceservice.viewmodels.InvoiceItemViewModel;
import com.company.invoiceservice.viewmodels.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceLayer {

    @Autowired
    private InvoiceDao  invoiceDao;

    @Autowired
    private InvoiceItemDao invoiceItemDao;

    public ServiceLayer(InvoiceDao invoiceDao, InvoiceItemDao invoiceItemDao) {
        this.invoiceDao = invoiceDao;
        this.invoiceItemDao = invoiceItemDao;
    }

    private InvoiceViewModel buildInvoiceViewModel(Invoice invoice){
        InvoiceViewModel ivm = new InvoiceViewModel(invoice.getCustomerId(), invoice.getPurchaseDate());
        ivm.setInvoiceId(invoice.getInvoiceId());
        return ivm;
    }

    public InvoiceViewModel createInvoice(InvoiceViewModel ivm){
        Invoice invoice = new Invoice(ivm.getCustomerId(), ivm.getPurchaseDate());
        invoice = invoiceDao.createInvoice(invoice);
        return buildInvoiceViewModel(invoice);
    }

    public InvoiceViewModel findInvoice(int id){
        Invoice invoice = invoiceDao.getInvoice(id);
        return buildInvoiceViewModel(invoice);
    }

    public List<InvoiceViewModel> findAllInvoices(){
        List<InvoiceViewModel> ivms = new ArrayList<>();
        invoiceDao.getAllInvoices().stream().forEach(invoice -> {
            InvoiceViewModel ivm = buildInvoiceViewModel(invoice);
            ivms.add(ivm);
        });
        return ivms;
    }

    public void updateInvoice(InvoiceViewModel ivm){
        Invoice invoice = new Invoice(ivm.getCustomerId(), ivm.getPurchaseDate());
        invoice.setInvoiceId(ivm.getInvoiceId());
        invoiceDao.updateInvoice(invoice);
    }

    public void deleteInvoice(int id){
        invoiceDao.deleteInvoice(id);
    }

    private InvoiceItemViewModel buildInvoiceItemViewModel(InvoiceItem invoiceItem){
        InvoiceItemViewModel iivm = new InvoiceItemViewModel(invoiceItem.getInvoiceId(), invoiceItem.getInventoryId(),
                invoiceItem.getQuantity(), invoiceItem.getUnitPrice());
        iivm.setInvoiceItemId(invoiceItem.getInvoiceItemId());

        return iivm;
    }

    public InvoiceItemViewModel createInvoiceItem(InvoiceItemViewModel iivm){
        InvoiceItem invoiceItem = new InvoiceItem(iivm.getInvoiceId(), iivm.getInventoryId(),
                iivm.getQuantity(), iivm.getUnitPrice());
        invoiceItem = invoiceItemDao.createInvoiceItem(invoiceItem);

        return buildInvoiceItemViewModel(invoiceItem);
    }

    public InvoiceItemViewModel findInvoiceItem(int id){
        InvoiceItem invoiceItem = invoiceItemDao.getInvoiceItem(id);
        return buildInvoiceItemViewModel(invoiceItem);
    }

    public List<InvoiceItemViewModel> findAllInvoiceItems(){
        List<InvoiceItemViewModel> iivms = new ArrayList<>();
        invoiceItemDao.getAllInvoiceItems().stream().forEach(invoiceItem -> {
            InvoiceItemViewModel iivm = buildInvoiceItemViewModel(invoiceItem);
            iivms.add(iivm);
        });
        return iivms;
    }

    public List<InvoiceItemViewModel> findInvoiceItemsByInvoiceId(int id){
        List<InvoiceItemViewModel> iivms = new ArrayList<>();
        invoiceItemDao.getInvoiceItemsByInvoiceId(id).stream().forEach(invoiceItem -> {
            InvoiceItemViewModel iivm = buildInvoiceItemViewModel(invoiceItem);
            iivms.add(iivm);
        });
        return iivms;
    }

    public void updateInvoiceItem(InvoiceItemViewModel iivm){
        InvoiceItem invoiceItem = new InvoiceItem(iivm.getInvoiceId(), iivm.getInventoryId(),
                iivm.getQuantity(), iivm.getUnitPrice());
        invoiceItem.setInvoiceItemId(iivm.getInvoiceItemId());
        invoiceItemDao.updateInvoiceItem(invoiceItem);
    }

    public void deleteInvoiceItem(int id){
        invoiceItemDao.deleteInvoiceItem(id);
    }

    public List<InvoiceViewModel> getInvoicesByCustomerId(int customerId){
        List<InvoiceViewModel> ivms = new ArrayList<>();
        invoiceDao.getInvoicesByCustomerId(customerId).stream().forEach(invoice -> {
            InvoiceViewModel ivm = buildInvoiceViewModel(invoice);
            ivms.add(ivm);
        });
        return ivms;
    }
}
