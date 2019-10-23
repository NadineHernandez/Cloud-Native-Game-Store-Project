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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

        this.mockMvc.perform(post("/invoice")
        .content(inputJson)
        .contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    void getAllInvoices() throws Exception{
        InvoiceViewModel outputInvoice = new InvoiceViewModel(1, LocalDate.of(2019, 7, 22));
        outputInvoice.setInvoice_id(1);

        List<InvoiceViewModel> ivms = new ArrayList<>();
        ivms.add(outputInvoice);

        when(serviceLayer.findAllInvoices()).thenReturn(ivms);

        List<InvoiceViewModel> listChecker = new ArrayList<>();
        listChecker.addAll(ivms);

        String outputJson = mapper.writeValueAsString(listChecker);

        this.mockMvc.perform(get("/invoice"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

    }

    @Test
    void getInvoice() throws Exception {
        InvoiceViewModel outputInvoice = new InvoiceViewModel(1, LocalDate.of(2019, 7, 22));
        outputInvoice.setInvoice_id(1);

        String outputJson = mapper.writeValueAsString(outputInvoice);

        when(serviceLayer.findInvoice(1)).thenReturn(outputInvoice);

        this.mockMvc.perform(get("/invoice/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    void getInvoiceThatDoesNotExistShouldReturn404()throws Exception{
        int id = 90000;

        when(serviceLayer.findInvoice(id)).thenReturn(null);

        this.mockMvc.perform(get("/invoice/" + id))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void updateInvoice() throws Exception{
        InvoiceViewModel inputInvoice = new InvoiceViewModel(1, LocalDate.of(2019, 7, 22));
        inputInvoice.setInvoice_id(1);

        String inputJson = mapper.writeValueAsString(inputInvoice);

        this.mockMvc.perform(put("/invoice")
        .content(inputJson)
        .contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void deleteInvoice() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/invoice/1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(""));
    }
}