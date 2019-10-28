package com.company.adminapi.service;

import com.company.adminapi.model.InventoryViewModel;
import com.company.adminapi.util.feign.InventoryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceLayer {

    @Autowired
    InventoryClient client;

    public Optional<InventoryViewModel> findInventory(int id) {

        return client.getInventory(id);
    }

    public List<InventoryViewModel> findAllInventories() {

        return client.getAllInventories();
    }

    public InventoryViewModel createInventory(InventoryViewModel ivm) {

        return client.addInventory(ivm);
    }

    public void updateInventory(InventoryViewModel ivm) {
        client.updateInventory(ivm);
    }

    public void deleteInventory(int id) {
        client.deleteInventory(id);
    }

}
