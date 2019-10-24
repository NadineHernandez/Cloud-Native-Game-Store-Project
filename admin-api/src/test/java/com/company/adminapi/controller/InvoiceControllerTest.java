package com.company.adminapi.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@WebMvcTest(InvoiceController.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
class InvoiceControllerTest {

    @Test
    void getInvoice() {
    }

    @Test
    void getAllInvoices() {
    }

    @Test
    void addInvoice() {
    }

    @Test
    void updateInvoice() {
    }

    @Test
    void deleteInvoice() {
    }
}