package com.company.adminapi.service;

import com.company.adminapi.util.feign.InvoiceClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class InvoiceServiceLayerTest {

    @InjectMocks
    InvoiceServiceLayer invoice;

    @Mock
    InvoiceClient client;

    @Test
    void findInvoice() {
    }

    @Test
    void findAllInvoices() {
    }

    @Test
    void createInvoice() {
    }

    @Test
    void updateInvoice() {
    }

    @Test
    void deleteInvoice() {
    }
}