package com.company.invoiceservice.controller;

import com.company.invoiceservice.service.ServiceLayer;
import com.company.invoiceservice.viewmodels.InvoiceViewModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = InvoiceController.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
class InvoiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServiceLayer serviceLayer;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    void addInvoice() throws Exception{
        InvoiceViewModel inputInvoice = new InvoiceViewModel(1, LocalDate.of(2019, 7, 22));
        String inputJson = mapper.writeValueAsString(inputInvoice);

        InvoiceViewModel outputInvoice = new InvoiceViewModel(1, LocalDate.of(2019, 7, 22));
        outputInvoice.setInvoice_id(1);
        String outputJson = mapper.writeValueAsString(outputInvoice);

        when(serviceLayer.createInvoice(inputInvoice)).thenReturn(outputInvoice);
    }

    @Test
    void getAllInvoices() {
    }

    @Test
    void getInvoice() {
    }

    @Test
    void updateInvoice() {
    }

    @Test
    void deleteInvoice() {
    }
}