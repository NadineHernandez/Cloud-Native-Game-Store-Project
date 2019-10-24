package com.company.inventoryservice.controller;

import com.company.inventoryservice.viewmodel.InventoryViewModel;
import com.company.inventoryservice.service.InventoryServiceLayer;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = InventoryController.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
class InventoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InventoryServiceLayer serviceLayer;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    void addInventory() throws Exception{
        InventoryViewModel inputIvm = new InventoryViewModel(1,10);

        String inputJson = mapper.writeValueAsString(inputIvm);

        InventoryViewModel outputIvm = new InventoryViewModel(1,10);
        outputIvm.setInventoryId(1);

        String outputJson = mapper.writeValueAsString(outputIvm);

        when(serviceLayer.createInventory(inputIvm)).thenReturn(outputIvm);

        this.mockMvc.perform(post("/inventory")
        .content(inputJson)
        .contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    void getAllInventories() throws Exception{
        InventoryViewModel outputIvm = new InventoryViewModel(1,10);
        outputIvm.setInventoryId(1);

        List<InventoryViewModel> ivmList = new ArrayList<>();
        ivmList.add(outputIvm);

        when(serviceLayer.findAllInventories()).thenReturn(ivmList);

        List<InventoryViewModel> listChecker = new ArrayList<>();
        listChecker.addAll(ivmList);

        String outputJson = mapper.writeValueAsString(listChecker);

        this.mockMvc.perform(get("/inventory"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    void getInventory() throws Exception{
        InventoryViewModel outputIvm = new InventoryViewModel(1,10);
        outputIvm.setInventoryId(1);

        String outputJson = mapper.writeValueAsString(outputIvm);

        when(serviceLayer.findInventory(1)).thenReturn(outputIvm);

        this.mockMvc.perform(get("/inventory/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    void getInventoryThatDoesNotExistShouldReturn404() throws Exception{
        int id = 90000;

        when(serviceLayer.findInventory(id)).thenReturn(null);

        this.mockMvc.perform(get("/inventory/" + id))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void updateInventory() throws Exception{
        InventoryViewModel inputIvm = new InventoryViewModel(1,10);
        inputIvm.setInventoryId(1);

        String inputJson = mapper.writeValueAsString(inputIvm);

        this.mockMvc.perform(put("/inventory")
        .content(inputJson)
        .contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void deleteInventory() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/inventory/1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(""));
    }
}