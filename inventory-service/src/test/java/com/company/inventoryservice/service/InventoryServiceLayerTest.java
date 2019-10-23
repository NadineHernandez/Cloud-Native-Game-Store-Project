package com.company.inventoryservice.service;

import com.company.inventoryservice.dao.InventoryDao;
import com.company.inventoryservice.dto.Inventory;
import com.company.inventoryservice.dto.InventoryViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class InventoryServiceLayerTest {

    private InventoryDao inventoryDao;
    private InventoryServiceLayer serviceLayer;

    private void setUpInventoryDaoMock(){
        inventoryDao = mock(InventoryDao.class);

        Inventory inventory = new Inventory(1, 10);
        inventory.setInventory_id(1);

        Inventory inventory1 = new Inventory(1, 10);

        List<Inventory> inventories = new ArrayList<>();
        inventories.add(inventory);

        doReturn(inventory).when(inventoryDao).createInventory(inventory1);
        doReturn(inventory).when(inventoryDao).getInventory(1);
        doReturn(inventories).when(inventoryDao).getAllInventories();
    }

    @BeforeEach
    void setUp() {
        setUpInventoryDaoMock();
        serviceLayer = new InventoryServiceLayer(inventoryDao);
    }

    @Test
    void createInventory() {
        InventoryViewModel ivm = new InventoryViewModel(1,10);
        ivm = serviceLayer.createInventory(ivm);

        assertEquals(ivm, serviceLayer.findInventory(ivm.getInventory_id()));
    }

    @Test
    void findInventory() {
        InventoryViewModel ivm = new InventoryViewModel(1,10);
        ivm = serviceLayer.createInventory(ivm);

        assertEquals(ivm, serviceLayer.findInventory(ivm.getInventory_id()));
    }

    @Test
    void findAllInventories() {
        InventoryViewModel ivm = new InventoryViewModel(1,10);
        serviceLayer.createInventory(ivm);

        assertEquals(1, serviceLayer.findAllInventories().size());
    }

    @Test
    void updateInventory() {
        InventoryViewModel ivm = new InventoryViewModel(1,10);
        ivm = serviceLayer.createInventory(ivm);

        ArgumentCaptor<Inventory> inventoryCaptor = ArgumentCaptor.forClass(Inventory.class);
        serviceLayer.updateInventory(ivm);

        verify(inventoryDao, times(1)).updateInventory(inventoryCaptor.capture());
    }

    @Test
    void deleteInventory() {
        InventoryViewModel ivm = new InventoryViewModel(1,10);
        ivm = serviceLayer.createInventory(ivm);

        ArgumentCaptor<Integer> intCaptor = ArgumentCaptor.forClass(Integer.class);
        serviceLayer.deleteInventory(ivm.getInventory_id());

        verify(inventoryDao, times(1)).deleteInventory(intCaptor.capture());
    }
}