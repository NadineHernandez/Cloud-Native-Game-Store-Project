package com.company.inventoryservice.dao;

import com.company.inventoryservice.dto.Inventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InventoryDaoTest {

    @Autowired
    InventoryDao inventoryDao;

    @BeforeEach
    void setUp() {
        inventoryDao.getAllInventories().stream()
                .forEach(inventory -> inventoryDao.deleteInventory(inventory.getInventoryId()));
    }

    @Test
    void createInventory() {
        Inventory inventory = new Inventory(1, 10);
        inventory = inventoryDao.createInventory(inventory);

        assertEquals(inventory, inventoryDao.getInventory(inventory.getInventoryId()));
    }

    @Test
    void getInventory() {
        Inventory inventory = new Inventory(1, 10);
        inventory = inventoryDao.createInventory(inventory);

        assertEquals(inventory, inventoryDao.getInventory(inventory.getInventoryId()));
    }

    @Test
    void getAllInventories() {
        Inventory inventory = new Inventory(1, 10);
        inventoryDao.createInventory(inventory);

        assertEquals(1, inventoryDao.getAllInventories().size());
    }

    @Test
    void updateInventory() {
        Inventory inventory = new Inventory(1, 10);
        inventory = inventoryDao.createInventory(inventory);

        inventory.setProductId(2);
        inventory.setQuantity(20);

        inventoryDao.updateInventory(inventory);

        assertEquals(inventory, inventoryDao.getInventory(inventory.getInventoryId()));
    }

    @Test
    void deleteInventory() {
        Inventory inventory = new Inventory(1, 10);
        inventory = inventoryDao.createInventory(inventory);
        assertEquals(inventory, inventoryDao.getInventory(inventory.getInventoryId()));

        inventoryDao.deleteInventory(inventory.getInventoryId());
        assertNull(inventoryDao.getInventory(inventory.getInventoryId()));
    }
}