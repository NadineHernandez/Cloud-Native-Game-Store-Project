package com.company.adminapi.service;

import com.company.adminapi.model.InvoiceItemViewModel;
import com.company.adminapi.model.InvoiceViewModel;
import com.company.adminapi.util.feign.InvoiceClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InvoiceServiceLayerTest {

    @InjectMocks
    InvoiceServiceLayer service;

    @Mock
    InvoiceClient client;

    @Test
    void findInvoice() {
        InvoiceViewModel ivm = new InvoiceViewModel(100001, 101, LocalDate.of(2019, 7, 22));
        when(client.getInvoice(ivm.getInvoiceId())).thenReturn(Optional.of(ivm));
        assertEquals(Optional.of(ivm), service.findInvoice(ivm.getInvoiceId()));

    }

    @Test
    void findAllInvoices() {
        InvoiceViewModel ivm = new InvoiceViewModel(100001, 101, LocalDate.of(2019, 7, 22));
        List<InvoiceViewModel> ivms = Collections.singletonList(ivm);
        when(client.getAllInvoices()).thenReturn(ivms);
        assertEquals(ivms, service.findAllInvoices());


    }

    @Test
    void createInvoice() {
        InvoiceViewModel ivm = new InvoiceViewModel(100001, 101, LocalDate.of(2019, 7, 22));
        InvoiceViewModel ivm2 = new InvoiceViewModel(101, LocalDate.of(2019, 7, 22));
        when(client.addInvoice(ivm2)).thenReturn(ivm);
        InvoiceViewModel fromService = service.createInvoice(ivm2);
        assertEquals(ivm, fromService);



    }

    @Test
    void updateInvoice() {
        InvoiceViewModel ivm = new InvoiceViewModel(100001, 101, LocalDate.of(2019, 7, 22));
        service.updateInvoice(ivm);
        verify(client, times(1)).updateInvoice(ivm);
    }

    @Test
    void deleteInvoice() {
        service.deleteInvoice(100001);
        verify(client, times(1)).deleteInvoice(100001);
    }

    @Test
    void findInvoiceItem() {
        InvoiceItemViewModel iivm = new InvoiceItemViewModel(1000001, 100001, 1001, 10, new BigDecimal("10.00"));
        when(client.getInvoiceItem(1000001)).thenReturn(Optional.of(iivm));
        assertEquals(Optional.of(iivm), service.findInvoiceItem(iivm.getInvoiceItemId()));
    }

    @Test
    void findAllInvoiceItems() {
        InvoiceItemViewModel iivm = new InvoiceItemViewModel(1000001, 100001, 1001, 10, new BigDecimal("10.00"));
        List<InvoiceItemViewModel> iivms = Collections.singletonList(iivm);
        when(client.getAllInvoiceItems()).thenReturn(iivms);
        assertEquals(iivms, service.findAllInvoiceItems());
    }

    @Test
    void createInvoiceItem() {
        InvoiceItemViewModel iivm = new InvoiceItemViewModel(1000001, 100001, 1001, 10, new BigDecimal("10.00"));
        InvoiceItemViewModel iivm2 = new InvoiceItemViewModel(100001, 1001, 10, new BigDecimal("10.00"));
        when(client.addInvoiceItem(iivm2)).thenReturn(iivm);
        InvoiceItemViewModel fromService = service.createInvoiceItem(iivm2);
    }

    @Test
    void findInvoiceItemsByInvoiceId() {
        InvoiceItemViewModel iivm = new InvoiceItemViewModel(1000001, 100001, 1001, 10, new BigDecimal("10.00"));
        List<InvoiceItemViewModel> iivms = Collections.singletonList(iivm);
        when(client.getInvoiceItemsByInvoiceId(100001)).thenReturn(iivms);
        assertEquals(iivms, service.findInvoiceItemsByInvoiceId(iivm.getInvoiceId()));
    }

    @Test
    void updateInvoiceItem() {
        InvoiceItemViewModel iivm = new InvoiceItemViewModel(1000001, 100001, 1001, 10, new BigDecimal("10.00"));
        service.updateInvoiceItem(iivm);
        verify(client, times(1)).updateInvoiceItem(iivm);
    }

    @Test
    void deleteInvoiceItem() {
        service.deleteInvoiceItem(1000001);
        verify(client, times(1)).deleteInvoiceItem(1000001);
    }
}