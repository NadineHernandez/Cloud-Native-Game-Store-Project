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
        return null;
    }

    public List<InventoryViewModel> findAllInventories() {
        return null;
    }

    public InventoryViewModel createInventory(InventoryViewModel ivm) {
        return null;
    }

    public void updateInventory(InventoryViewModel ivm) {

    }

    public void deleteInventory(int id) {

    }

}
