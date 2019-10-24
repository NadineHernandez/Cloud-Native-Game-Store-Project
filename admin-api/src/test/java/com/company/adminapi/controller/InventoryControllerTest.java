package com.company.adminapi.controller;

import com.company.adminapi.model.InventoryViewModel;
import com.company.adminapi.service.InventoryServiceLayer;
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

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(InventoryController.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
class InventoryControllerTest {


    @MockBean
    InventoryServiceLayer service;

    @Autowired
    MockMvc mockMvc;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    void getInventory() throws Exception{
        InventoryViewModel ivm = new InventoryViewModel(1001, 1,10);
        when(service.findInventory(ivm.getInventoryId())).thenReturn(Optional.of(ivm));
        String outputJson = mapper.writeValueAsString(ivm);
        mockMvc.perform(get("/inventory/1001"))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

        mockMvc.perform(get("/inventory/9999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllInventories() throws Exception {
        InventoryViewModel ivm = new InventoryViewModel(1001, 1,10);
        List<InventoryViewModel> ivms = Collections.singletonList(ivm);
        when(service.findAllInventories()).thenReturn(ivms);
        String outputJson = mapper.writeValueAsString(ivms);
        mockMvc.perform(get("/inventory"))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    void addInventory() throws Exception{
        InventoryViewModel ivm = new InventoryViewModel(1001, 1,10);
        InventoryViewModel ivm2 = new InventoryViewModel(1, 10);
        when(service.createInventory(ivm2)).thenReturn(ivm);
        String inputJson = mapper.writeValueAsString(ivm2);
        String outputJson = mapper.writeValueAsString(ivm);
        mockMvc.perform(post("/inventory")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    void updateInventory() throws Exception{

        InventoryViewModel ivm = new InventoryViewModel(1001, 1,10);
        String inputJson = mapper.writeValueAsString(ivm);
        mockMvc.perform(put("/inventory")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteInventory() throws Exception{
        mockMvc.perform(delete("/inventory/1001"))
                .andExpect(status().isNoContent());
    }
}