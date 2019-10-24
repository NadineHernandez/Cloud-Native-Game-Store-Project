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
        InvoiceViewModel ivm = new InvoiceViewModel(invoice.getCustomer_id(), invoice.getPurchase_date());
        ivm.setInvoice_id(invoice.getInvoice_id());
        return ivm;
    }

    public InvoiceViewModel createInvoice(InvoiceViewModel ivm){
        Invoice invoice = new Invoice(ivm.getCustomer_id(), ivm.getPurchase_date());
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
        Invoice invoice = new Invoice(ivm.getCustomer_id(), ivm.getPurchase_date());
        invoice.setInvoice_id(ivm.getInvoice_id());
        invoiceDao.updateInvoice(invoice);
    }

    public void deleteInvoice(int id){
        invoiceDao.deleteInvoice(id);
    }

    private InvoiceItemViewModel buildInvoiceItemViewModel(InvoiceItem invoiceItem){
        InvoiceItemViewModel iivm = new InvoiceItemViewModel(invoiceItem.getInvoice_id(), invoiceItem.getInventory_id(),
                invoiceItem.getQuantity(), invoiceItem.getUnit_price());
        iivm.setInvoice_item_id(invoiceItem.getInvoice_item_id());

        return iivm;
    }

    public InvoiceItemViewModel createInvoiceItem(InvoiceItemViewModel iivm){
        InvoiceItem invoiceItem = new InvoiceItem(iivm.getInvoice_id(), iivm.getInventory_id(),
                iivm.getQuantity(), iivm.getUnit_price());
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
        InvoiceItem invoiceItem = new InvoiceItem(iivm.getInvoice_id(), iivm.getInventory_id(),
                iivm.getQuantity(), iivm.getUnit_price());
        invoiceItem.setInvoice_item_id(iivm.getInvoice_item_id());
        invoiceItemDao.updateInvoiceItem(invoiceItem);
    }

    public void deleteInvoiceItem(int id){
        invoiceItemDao.deleteInvoiceItem(id);
    }
}
