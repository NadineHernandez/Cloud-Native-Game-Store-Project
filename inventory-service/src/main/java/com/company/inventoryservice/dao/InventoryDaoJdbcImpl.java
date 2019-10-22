package com.company.inventoryservice.dao;

import com.company.inventoryservice.dto.Inventory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InventoryDaoJdbcImpl implements InventoryDao{
    //prepared statements
    public static final String INSERT_INVENTORY_SQL =
            "INSERT INTO inventory ";
    @Override
    public Inventory createInventory(Inventory inventory) {
        return null;
    }

    @Override
    public Inventory getInventory(int id) {
        return null;
    }

    @Override
    public List<Inventory> getAllInventories() {
        return null;
    }

    @Override
    public void updateInventory(Inventory inventory) {

    }

    @Override
    public void deleteInventory(int id) {

    }
}
