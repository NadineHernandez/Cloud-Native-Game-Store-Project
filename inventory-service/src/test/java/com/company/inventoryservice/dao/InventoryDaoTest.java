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
                .forEach(inventory -> inventoryDao.deleteInventory(inventory.getInventory_id()));
    }

    @Test
    void createInventory() {
        Inventory inventory = new Inventory(1, 10);
        inventory = inventoryDao.createInventory(inventory);

        assertEquals(inventory, inventoryDao.getInventory(inventory.getInventory_id()));
    }

    @Test
    void getInventory() {
        Inventory inventory = new Inventory(1, 10);
        inventory = inventoryDao.createInventory(inventory);

        assertEquals(inventory, inventoryDao.getInventory(inventory.getInventory_id()));
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

        inventory.setProduct_id(2);
        inventory.setQuantity(20);

        inventoryDao.updateInventory(inventory);

        assertEquals(inventory, inventoryDao.getInventory(inventory.getInventory_id()));
    }

    @Test
    void deleteInventory() {
        Inventory inventory = new Inventory(1, 10);
        inventory = inventoryDao.createInventory(inventory);
        assertEquals(inventory, inventoryDao.getInventory(inventory.getInventory_id()));

        inventoryDao.deleteInventory(inventory.getInventory_id());
        assertNull(inventoryDao.getInventory(inventory.getInventory_id()));
    }
}