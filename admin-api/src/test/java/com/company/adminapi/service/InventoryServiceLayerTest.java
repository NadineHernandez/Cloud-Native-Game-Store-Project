package com.company.adminapi.service;

import com.company.adminapi.model.InventoryViewModel;
import com.company.adminapi.util.feign.InventoryClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InventoryServiceLayerTest {

    @InjectMocks
    InventoryServiceLayer service;

    @Mock
    InventoryClient client;

    @Test
    void findInventory() {
        InventoryViewModel ivm = new InventoryViewModel(1001, 1,10);
        when(client.getInventory(ivm.getInventoryId())).thenReturn(Optional.of(ivm));
        assertEquals(Optional.of(ivm), service.findInventory(ivm.getInventoryId()));
    }

    @Test
    void findAllInventories() {
        InventoryViewModel ivm = new InventoryViewModel(1001, 1,10);
        List<InventoryViewModel> ivms = Collections.singletonList(ivm);
        when(client.getAllInventories()).thenReturn(ivms);
        assertEquals(ivms, service.findAllInventories());
    }

    @Test
    void createInventory() {
        InventoryViewModel ivm = new InventoryViewModel(1001, 1,10);
        InventoryViewModel ivm2 = new InventoryViewModel(1, 10);
        when(client.addInventory(ivm2)).thenReturn(ivm);
        InventoryViewModel fromService = service.createInventory(ivm2);
        assertEquals(ivm, fromService);
    }

    @Test
    void updateInventory() {
        InventoryViewModel ivm = new InventoryViewModel(1001, 1,10);
        service.updateInventory(ivm);
        verify(client, times(1)).updateInventory(ivm);
    }

    @Test
    void deleteInventory() {
        service.deleteInventory(1001);
        verify(client, times(1)).deleteInventory(1001);
    }
}