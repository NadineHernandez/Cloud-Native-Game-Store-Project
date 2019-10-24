package com.company.inventoryservice.service;

import com.company.inventoryservice.dao.InventoryDao;
import com.company.inventoryservice.dto.Inventory;
import com.company.inventoryservice.viewmodel.InventoryViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryServiceLayer {
    @Autowired
    InventoryDao inventoryDao;

    public InventoryServiceLayer(InventoryDao inventoryDao) {
        this.inventoryDao = inventoryDao;
    }

    private InventoryViewModel buildInventoryViewModel(Inventory inventory){
        InventoryViewModel ivm = new InventoryViewModel(inventory.getProductId(), inventory.getQuantity());
        ivm.setInventoryId(inventory.getInventoryId());
        return ivm;
    }

    public InventoryViewModel createInventory(InventoryViewModel ivm){
        Inventory inventory = new Inventory(ivm.getProductId(), ivm.getQuantity());
        inventory = inventoryDao.createInventory(inventory);
        return buildInventoryViewModel(inventory);
    }

    public InventoryViewModel findInventory(int id){
        Inventory inventory = inventoryDao.getInventory(id);
        return buildInventoryViewModel(inventory);
    }

    public List<InventoryViewModel> findAllInventories(){
        List<InventoryViewModel> ivmList = new ArrayList<>();
        inventoryDao.getAllInventories().stream().forEach(inventory -> {
            InventoryViewModel ivm = buildInventoryViewModel(inventory);
            ivmList.add(ivm);
        });
        return ivmList;
    }

    public void updateInventory(InventoryViewModel ivm){
        Inventory inventory = new Inventory(ivm.getProductId(), ivm.getQuantity());
        inventory.setInventoryId(ivm.getInventoryId());
        inventoryDao.updateInventory(inventory);
    }

    public void deleteInventory(int id){
        inventoryDao.deleteInventory(id);
    }
}
