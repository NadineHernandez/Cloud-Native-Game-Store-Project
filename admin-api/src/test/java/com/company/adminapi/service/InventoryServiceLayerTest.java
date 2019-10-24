package com.company.adminapi.service;

import com.company.adminapi.util.feign.InventoryClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class InventoryServiceLayerTest {

    @InjectMocks
    InventoryServiceLayer service;

    @Mock
    InventoryClient client;

    @Test
    void findInventory() {
    }

    @Test
    void findAllInventories() {
    }

    @Test
    void createInventory() {
    }

    @Test
    void updateInventory() {
    }

    @Test
    void deleteInventory() {
    }
}