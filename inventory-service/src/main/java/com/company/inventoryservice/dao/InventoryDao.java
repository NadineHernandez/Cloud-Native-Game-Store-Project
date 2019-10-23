package com.company.inventoryservice.dao;

import com.company.inventoryservice.dto.Inventory;

import java.util.List;

public interface InventoryDao {
    Inventory createInventory(Inventory inventory);
    Inventory getInventory(int id);
    List<Inventory> getAllInventories();
    void updateInventory(Inventory inventory);
    void deleteInventory(int id);
}
