package com.company.invoiceservice.service;

import com.company.invoiceservice.dao.InvoiceDao;
import com.company.invoiceservice.dao.InvoiceItemDao;
import com.company.invoiceservice.dto.Invoice;
import com.company.invoiceservice.dto.InvoiceItem;
import com.company.invoiceservice.viewmodels.InvoiceItemViewModel;
import com.company.invoiceservice.viewmodels.InvoiceViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ServiceLayerTest {

    private InvoiceDao invoiceDao;
    private InvoiceItemDao invoiceItemDao;

    private ServiceLayer serviceLayer;

    private void setUpInvoiceDaoMock(){
        invoiceDao = mock(InvoiceDao.class);
        Invoice invoice = new Invoice(1, LocalDate.of(2019, 7, 22));
        invoice.setInvoice_id(1);

        Invoice invoice1 = new Invoice(1, LocalDate.of(2019, 7, 22));

        List<Invoice> invoices = new ArrayList<>();
        invoices.add(invoice);

        doReturn(invoice).when(invoiceDao).createInvoice(invoice1);
        doReturn(invoice).when(invoiceDao).getInvoice(1);
        doReturn(invoices).when(invoiceDao).getAllInvoices();
    }

    private void setUpInvoiceItemDaoMock(){
        invoiceItemDao = mock(InvoiceItemDao.class);
        InvoiceItem invoiceItem = new InvoiceItem(1, 1, 10, new BigDecimal("10.00"));
        invoiceItem.setInvoice_item_id(1);

        InvoiceItem invoiceItem1 = new InvoiceItem(1, 1, 10, new BigDecimal("10.00"));

        List<InvoiceItem> invoiceItems = new ArrayList<>();
        invoiceItems.add(invoiceItem);

        doReturn(invoiceItem).when(invoiceItemDao).createInvoiceItem(invoiceItem1);
        doReturn(invoiceItem).when(invoiceItemDao).getInvoiceItem(1);
        doReturn(invoiceItems).when(invoiceItemDao).getAllInvoiceItems();
        doReturn(invoiceItems).when(invoiceItemDao).getInvoiceItemsByInvoiceId(1);
    }

    @BeforeEach
    void setUp() {
        setUpInvoiceDaoMock();
        setUpInvoiceItemDaoMock();
        serviceLayer = new ServiceLayer(invoiceDao, invoiceItemDao);
    }

    @Test
    void createInvoice() {
        InvoiceViewModel invoice1 = new InvoiceViewModel(1, LocalDate.of(2019, 7, 22));
        invoice1 = serviceLayer.createInvoice(invoice1);

        assertEquals(invoice1, serviceLayer.findInvoice(invoice1.getInvoice_id()));
    }

    @Test
    void findInvoice() {
        InvoiceViewModel invoice1 = new InvoiceViewModel(1, LocalDate.of(2019, 7, 22));
        invoice1 = serviceLayer.createInvoice(invoice1);

        assertEquals(invoice1, serviceLayer.findInvoice(invoice1.getInvoice_id()));
    }

    @Test
    void findAllInvoices() {
        InvoiceViewModel invoice1 = new InvoiceViewModel(1, LocalDate.of(2019, 7, 22));
        serviceLayer.createInvoice(invoice1);

        assertEquals(1, serviceLayer.findAllInvoices().size());
    }

    @Test
    void updateInvoice() {
        InvoiceViewModel invoice1 = new InvoiceViewModel(1, LocalDate.of(2019, 7, 22));
        serviceLayer.createInvoice(invoice1);

        ArgumentCaptor<Invoice> invoiceCaptor = ArgumentCaptor.forClass(Invoice.class);
        serviceLayer.updateInvoice(invoice1);

        verify(invoiceDao, times(1)).updateInvoice(invoiceCaptor.capture());
    }

    @Test
    void deleteInvoice() {
        InvoiceViewModel invoice1 = new InvoiceViewModel(1, LocalDate.of(2019, 7, 22));
        serviceLayer.createInvoice(invoice1);

        ArgumentCaptor<Integer> intCaptor = ArgumentCaptor.forClass(Integer.class);
        serviceLayer.deleteInvoice(invoice1.getInvoice_id());

        verify(invoiceDao, times(1)).deleteInvoice(intCaptor.capture());
    }

    @Test
    void createInvoiceItem() {
        InvoiceItemViewModel invoiceItem = new InvoiceItemViewModel(1, 1, 10, new BigDecimal("10.00"));
        invoiceItem = serviceLayer.createInvoiceItem(invoiceItem);

        assertEquals(invoiceItem, serviceLayer.findInvoiceItem(invoiceItem.getInvoice_item_id()));
    }

    @Test
    void findInvoiceItem() {
        InvoiceItemViewModel invoiceItem = new InvoiceItemViewModel(1, 1, 10, new BigDecimal("10.00"));
        invoiceItem = serviceLayer.createInvoiceItem(invoiceItem);

        assertEquals(invoiceItem, serviceLayer.findInvoiceItem(invoiceItem.getInvoice_item_id()));
    }

    @Test
    void findAllInvoiceItems() {
        InvoiceItemViewModel invoiceItem = new InvoiceItemViewModel(1, 1, 10, new BigDecimal("10.00"));
        serviceLayer.createInvoiceItem(invoiceItem);

        assertEquals(1, serviceLayer.findAllInvoiceItems().size());
    }

    @Test
    void findInvoiceItemsByInvoiceId(){
        InvoiceItemViewModel invoiceItem = new InvoiceItemViewModel(1, 1, 10, new BigDecimal("10.00"));
        invoiceItem = serviceLayer.createInvoiceItem(invoiceItem);

        assertEquals(1, invoiceItemDao.getInvoiceItemsByInvoiceId(invoiceItem.getInvoice_item_id()).size());
    }

    @Test
    void updateInvoiceItem() {
        InvoiceItemViewModel invoiceItem = new InvoiceItemViewModel(1, 1, 10, new BigDecimal("10.00"));
        invoiceItem = serviceLayer.createInvoiceItem(invoiceItem);

        ArgumentCaptor<InvoiceItem> itemCaptor = ArgumentCaptor.forClass(InvoiceItem.class);
        serviceLayer.updateInvoiceItem(invoiceItem);

        verify(invoiceItemDao, times(1)).updateInvoiceItem(itemCaptor.capture());
    }

    @Test
    void deleteInvoiceItem() {
        InvoiceItemViewModel invoiceItem = new InvoiceItemViewModel(1, 1, 10, new BigDecimal("10.00"));
        invoiceItem = serviceLayer.createInvoiceItem(invoiceItem);

        ArgumentCaptor<Integer> intCaptor = ArgumentCaptor.forClass(Integer.class);
        serviceLayer.deleteInvoiceItem(invoiceItem.getInvoice_item_id());

        verify(invoiceItemDao, times(1)).deleteInvoiceItem(intCaptor.capture());
    }
}