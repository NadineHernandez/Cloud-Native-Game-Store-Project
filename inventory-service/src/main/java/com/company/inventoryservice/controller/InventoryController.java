package com.company.inventoryservice.controller;

import com.company.inventoryservice.viewmodel.InventoryViewModel;
import com.company.inventoryservice.service.InventoryServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RefreshScope
@RestController
public class InventoryController {
    @Autowired
    InventoryServiceLayer serviceLayer;

    @PostMapping(value = "/inventory")
    @ResponseStatus(HttpStatus.CREATED)
    public InventoryViewModel addInventory(@RequestBody @Valid InventoryViewModel ivm){
        return serviceLayer.createInventory(ivm);
    }

    @GetMapping(value = "/inventory")
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryViewModel> getAllInventories(){
        return serviceLayer.findAllInventories();
    }

    @GetMapping(value = "/inventory/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InventoryViewModel getInventory(@PathVariable int id){
        try{
            int tester = serviceLayer.findInventory(id).getInventoryId();
        }catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No Inventory found with id: " + id, e
            );
        }
        return serviceLayer.findInventory(id);
    }

    @PutMapping(value = "/inventory")
    @ResponseStatus(HttpStatus.OK)
    public void updateInventory(@RequestBody @Valid InventoryViewModel ivm){
        serviceLayer.updateInventory(ivm);
    }

    @DeleteMapping(value = "/inventory/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteInvoice(@PathVariable int id){
        serviceLayer.deleteInventory(id);
    }
}
