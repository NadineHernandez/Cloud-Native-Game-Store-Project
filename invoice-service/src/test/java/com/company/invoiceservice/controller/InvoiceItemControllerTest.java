package com.company.invoiceservice.controller;

import com.company.invoiceservice.service.ServiceLayer;
import com.company.invoiceservice.viewmodels.InvoiceItemViewModel;
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

import java.math.BigDecimal;
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
@WebMvcTest(controllers = InvoiceItemController.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
class InvoiceItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServiceLayer serviceLayer;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    void addInvoiceItem() throws Exception{
        InvoiceItemViewModel inputItem = new InvoiceItemViewModel(1, 1, 10, new BigDecimal("10.00"));
        String inputJson = mapper.writeValueAsString(inputItem);

        InvoiceItemViewModel outputItem = new InvoiceItemViewModel(1, 1, 10, new BigDecimal("10.00"));
        outputItem.setInvoice_item_id(1);
        String outputJson = mapper.writeValueAsString(outputItem);

        when(serviceLayer.createInvoiceItem(inputItem)).thenReturn(outputItem);

        this.mockMvc.perform(post("/invoiceitem")
        .content(inputJson)
        .contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    void getAllInvoiceItems() throws Exception{
        InvoiceItemViewModel outputItem = new InvoiceItemViewModel(1, 1, 10, new BigDecimal("10.00"));
        outputItem.setInvoice_item_id(1);

        List<InvoiceItemViewModel> items = new ArrayList<>();
        items.add(outputItem);

        when(serviceLayer.findAllInvoiceItems()).thenReturn(items);

        List<InvoiceItemViewModel> listChecker = new ArrayList<>();
        listChecker.addAll(items);

        String outputJson = mapper.writeValueAsString(listChecker);

        this.mockMvc.perform(get("/invoiceitem"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    void getInvoiceItem() throws Exception{
        InvoiceItemViewModel outputItem = new InvoiceItemViewModel(1, 1, 10, new BigDecimal("10.00"));
        outputItem.setInvoice_item_id(1);

        String outputJson = mapper.writeValueAsString(outputItem);

        when(serviceLayer.findInvoiceItem(1)).thenReturn(outputItem);

        this.mockMvc.perform(get("/invoiceitem/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    void getInvoiceItemThatDoesNotExistShouldReturn404() throws Exception{
        int id = 90000;

        when(serviceLayer.findInvoiceItem(id)).thenReturn(null);

        this.mockMvc.perform(get("/invoiceitem/" + id))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void getInvoiceItemsByInvoiceId() throws Exception{
        InvoiceItemViewModel outputItem = new InvoiceItemViewModel(1, 1, 10, new BigDecimal("10.00"));
        outputItem.setInvoice_item_id(1);

        InvoiceViewModel outputInvoice = new InvoiceViewModel(1, LocalDate.of(2019, 7, 22));
        outputInvoice.setInvoice_id(1);

        List<InvoiceItemViewModel> items = new ArrayList<>();
        items.add(outputItem);

        when(serviceLayer.findInvoiceItemsByInvoiceId(1)).thenReturn(items);
        when(serviceLayer.findInvoice(1)).thenReturn(outputInvoice);

        List<InvoiceItemViewModel> listChecker = new ArrayList<>();
        listChecker.addAll(items);

        String outputJson = mapper.writeValueAsString(listChecker);

        this.mockMvc.perform(get("/invoiceitem/invoice/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    void getInvoiceItemsByInvoiceIdThatDoesNotExistShouldReturn404()throws Exception{
        int id = 90000;

        when(serviceLayer.findInvoiceItemsByInvoiceId(id)).thenReturn(null);

        this.mockMvc.perform(get("/invoiceitem/invoice/1"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void updateInvoiceItem() throws Exception{
        InvoiceItemViewModel inputItem = new InvoiceItemViewModel(1, 1, 10, new BigDecimal("10.00"));
        inputItem.setInvoice_item_id(1);

        String inputJson = mapper.writeValueAsString(inputItem);

        this.mockMvc.perform(put("/invoiceitem")
        .content(inputJson)
        .contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void deleteInvoiceItem() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/invoiceitem/1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(""));
    }
}