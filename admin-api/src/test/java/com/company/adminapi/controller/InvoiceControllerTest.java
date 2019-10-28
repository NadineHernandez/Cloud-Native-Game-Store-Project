package com.company.adminapi.controller;

import com.company.adminapi.model.InvoiceItemViewModel;
import com.company.adminapi.model.InvoiceViewModel;
import com.company.adminapi.service.InvoiceServiceLayer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(InvoiceController.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
class InvoiceControllerTest {


    @MockBean
    DataSource dataSource;

    @MockBean
    InvoiceServiceLayer service;

    @Autowired
    MockMvc mockMvc;

    ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    @Test
    @WithMockUser(username="adminUser", roles = {"ADMIN"})
    void getInvoice() throws Exception {
        InvoiceViewModel ivm = new InvoiceViewModel(100001, 101, LocalDate.of(2019, 7, 22));
        when(service.findInvoice(ivm.getInvoiceId())).thenReturn(Optional.of(ivm));
        String outputJson = mapper.writeValueAsString(ivm);
        System.out.println(outputJson);
        mockMvc.perform(get("/invoice/100001")
                .with(csrf().asHeader()))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
        mockMvc.perform(get("/invoice/999999")
                .with(csrf().asHeader()))
                .andExpect(status().isNotFound());


    }

    @Test
    @WithMockUser(username="adminUser", roles = {"ADMIN"})
    void getAllInvoices() throws Exception {
        InvoiceViewModel ivm = new InvoiceViewModel(100001, 101, LocalDate.of(2019, 7, 22));

        List<InvoiceViewModel> ivms = Collections.singletonList(ivm);
        when(service.findAllInvoices()).thenReturn(ivms);
        String outputJson = mapper.writeValueAsString(ivms);
        mockMvc.perform(get("/invoice")
                .with(csrf().asHeader()))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

    }

    @Test
    @WithMockUser(username="adminUser", roles = {"ADMIN"})
    void addInvoice() throws Exception {
        InvoiceViewModel ivm = new InvoiceViewModel(100001, 101, LocalDate.of(2019, 7, 22));
        InvoiceViewModel ivm2 = new InvoiceViewModel(101, LocalDate.of(2019, 7, 22));
        when(service.createInvoice(ivm2)).thenReturn(ivm);
        String inputJson = mapper.writeValueAsString(ivm2);
        String outputJson = mapper.writeValueAsString(ivm);
        mockMvc.perform((post("/invoice")
                .with(csrf().asHeader())
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)))
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    @WithMockUser(username="adminUser", roles = {"ADMIN"})
    void updateInvoice() throws Exception {
        InvoiceViewModel ivm = new InvoiceViewModel(100001, 101, LocalDate.of(2019, 7, 22));
        String inputJson = mapper.writeValueAsString(ivm);
        mockMvc.perform(put("/invoice")
                .with(csrf().asHeader())
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(username="adminUser", roles = {"ADMIN"})
    void deleteInvoice() throws Exception {
        mockMvc.perform(delete("/invoice/100001")
                .with(csrf().asHeader()))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(username="adminUser", roles = {"ADMIN"})
    void getInvoiceItem() throws Exception {
        InvoiceItemViewModel iivm = new InvoiceItemViewModel(1000001, 100001, 1001, 10, new BigDecimal("10.00"));
        String outputJson = mapper.writeValueAsString(iivm);
        when(service.findInvoiceItem(iivm.getInvoiceItemId())).thenReturn(Optional.of(iivm));
        mockMvc.perform(get("/invoiceitem/1000001")
                .with(csrf().asHeader()))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
        mockMvc.perform(get("/invoiceitem/9999999")
                .with(csrf().asHeader()))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username="adminUser", roles = {"ADMIN"})
    void getAllInvoiceItems() throws Exception {
        InvoiceItemViewModel iivm = new InvoiceItemViewModel(1000001, 100001, 1001, 10, new BigDecimal("10.00"));
        List<InvoiceItemViewModel> iivms = Collections.singletonList(iivm);
        when(service.findAllInvoiceItems()).thenReturn(iivms);
        String outputJson = mapper.writeValueAsString(iivms);
        mockMvc.perform(get("/invoiceitem/")
                .with(csrf().asHeader()))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

    }

    @Test
    @WithMockUser(username="adminUser", roles = {"ADMIN"})
    void addInvoiceItem() throws Exception {
        InvoiceItemViewModel iivm = new InvoiceItemViewModel(1000001, 100001, 1001, 10, new BigDecimal("10.00"));
        InvoiceItemViewModel iivm2 = new InvoiceItemViewModel(100001, 1001, 10, new BigDecimal("10.00"));
        String inputJson = mapper.writeValueAsString(iivm2);
        String outputJson = mapper.writeValueAsString(iivm);
        when(service.createInvoiceItem(iivm2)).thenReturn(iivm);
        mockMvc.perform(post("/invoiceitem")
                .with(csrf().asHeader())
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    @WithMockUser(username="adminUser", roles = {"ADMIN"})
    void getInvoiceItemsByInvoiceId() throws Exception {
        InvoiceItemViewModel iivm = new InvoiceItemViewModel(1000001, 100001, 1001, 10, new BigDecimal("10.00"));
        List<InvoiceItemViewModel> iivms = Collections.singletonList(iivm);
        when(service.findInvoiceItemsByInvoiceId(iivm.getInvoiceId())).thenReturn(iivms);
        String outputJson = mapper.writeValueAsString(iivms);
        mockMvc.perform(get("/invoiceitem/invoice/100001")
                .with(csrf().asHeader()))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    @WithMockUser(username="adminUser", roles = {"ADMIN"})
    void updateInvoiceItem() throws Exception {
        InvoiceItemViewModel iivm = new InvoiceItemViewModel(1000001, 100001, 1001, 10, new BigDecimal("10.00"));
        String inputJson = mapper.writeValueAsString(iivm);
        mockMvc.perform(put("/invoiceitem")
                .with(csrf().asHeader())
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(username="adminUser", roles = {"ADMIN"})
    void deleteInvoiceItem() throws Exception {
        mockMvc.perform(delete("/invoiceitem/1000001")
                .with(csrf().asHeader()))
                .andExpect(status().isNoContent());
    }

    //copies of tests without authorization

    @Test
    void getInvoiceNoAuth() throws Exception {
        InvoiceViewModel ivm = new InvoiceViewModel(100001, 101, LocalDate.of(2019, 7, 22));
        when(service.findInvoice(ivm.getInvoiceId())).thenReturn(Optional.of(ivm));
        String outputJson = mapper.writeValueAsString(ivm);
        System.out.println(outputJson);
        mockMvc.perform(get("/invoice/100001"))
                .andExpect(status().isUnauthorized());
        mockMvc.perform(get("/invoice/999999"))
                .andExpect(status().isUnauthorized());


    }

    @Test
    void getAllInvoicesNoAuth() throws Exception {
        InvoiceViewModel ivm = new InvoiceViewModel(100001, 101, LocalDate.of(2019, 7, 22));

        List<InvoiceViewModel> ivms = Collections.singletonList(ivm);
        when(service.findAllInvoices()).thenReturn(ivms);
        String outputJson = mapper.writeValueAsString(ivms);
        mockMvc.perform(get("/invoice"))
                .andExpect(status().isUnauthorized());

    }

    @Test
    void addInvoiceNoAuth() throws Exception {
        InvoiceViewModel ivm = new InvoiceViewModel(100001, 101, LocalDate.of(2019, 7, 22));
        InvoiceViewModel ivm2 = new InvoiceViewModel(101, LocalDate.of(2019, 7, 22));
        when(service.createInvoice(ivm2)).thenReturn(ivm);
        String inputJson = mapper.writeValueAsString(ivm2);
        String outputJson = mapper.writeValueAsString(ivm);
        mockMvc.perform((post("/invoice")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)))
                .andExpect(status().isForbidden());
    }

    @Test
    void updateInvoiceNoAuth() throws Exception {
        InvoiceViewModel ivm = new InvoiceViewModel(100001, 101, LocalDate.of(2019, 7, 22));
        String inputJson = mapper.writeValueAsString(ivm);
        mockMvc.perform(put("/invoice")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    void deleteInvoiceNoAuth() throws Exception {
        mockMvc.perform(delete("/invoice/100001"))
                .andExpect(status().isForbidden());
    }

    @Test
    void getInvoiceItemNoAuth() throws Exception {
        InvoiceItemViewModel iivm = new InvoiceItemViewModel(1000001, 100001, 1001, 10, new BigDecimal("10.00"));
        String outputJson = mapper.writeValueAsString(iivm);
        when(service.findInvoiceItem(iivm.getInvoiceItemId())).thenReturn(Optional.of(iivm));
        mockMvc.perform(get("/invoiceitem/1000001"))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
        mockMvc.perform(get("/invoiceitem/9999999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllInvoiceItemsNoAuth() throws Exception {
        InvoiceItemViewModel iivm = new InvoiceItemViewModel(1000001, 100001, 1001, 10, new BigDecimal("10.00"));
        List<InvoiceItemViewModel> iivms = Collections.singletonList(iivm);
        when(service.findAllInvoiceItems()).thenReturn(iivms);
        String outputJson = mapper.writeValueAsString(iivms);
        mockMvc.perform(get("/invoiceitem/"))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

    }

    @Test
    void addInvoiceItemNoAuth() throws Exception {
        InvoiceItemViewModel iivm = new InvoiceItemViewModel(1000001, 100001, 1001, 10, new BigDecimal("10.00"));
        InvoiceItemViewModel iivm2 = new InvoiceItemViewModel(100001, 1001, 10, new BigDecimal("10.00"));
        String inputJson = mapper.writeValueAsString(iivm2);
        String outputJson = mapper.writeValueAsString(iivm);
        when(service.createInvoiceItem(iivm2)).thenReturn(iivm);
        mockMvc.perform(post("/invoiceitem")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    void getInvoiceItemsByInvoiceIdNoAuth() throws Exception {
        InvoiceItemViewModel iivm = new InvoiceItemViewModel(1000001, 100001, 1001, 10, new BigDecimal("10.00"));
        List<InvoiceItemViewModel> iivms = Collections.singletonList(iivm);
        when(service.findInvoiceItemsByInvoiceId(iivm.getInvoiceId())).thenReturn(iivms);
        String outputJson = mapper.writeValueAsString(iivms);
        mockMvc.perform(get("/invoiceitem/invoice/100001"))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    void updateInvoiceItemNoAuth() throws Exception {
        InvoiceItemViewModel iivm = new InvoiceItemViewModel(1000001, 100001, 1001, 10, new BigDecimal("10.00"));
        String inputJson = mapper.writeValueAsString(iivm);
        mockMvc.perform(put("/invoiceitem")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    void deleteInvoiceItemNoAuth() throws Exception {
        mockMvc.perform(delete("/invoiceitem/1000001"))
                .andExpect(status().isForbidden());
    }

}