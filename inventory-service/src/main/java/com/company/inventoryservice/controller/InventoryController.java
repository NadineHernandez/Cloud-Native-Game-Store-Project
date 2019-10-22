package com.company.inventoryservice.controller;

import com.company.inventoryservice.dto.Inventory;
import com.company.inventoryservice.dto.InventoryViewModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public class InventoryController {
    @PostMapping(value = "/inventory")
    @ResponseStatus(HttpStatus.CREATED)
    public Inventory addInventory(@RequestBody @Valid InventoryViewModel ivm){
        return null;
    }

    @GetMapping(value = "/inventory")
    @ResponseStatus(HttpStatus.OK)
    public List<Inventory> getAllInventories(){
        return null;
    }

    @GetMapping(value = "/inventory/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Inventory getInventory(@PathVariable int id){
        return null;
    }

    @PutMapping(value = "/inventory")
    @ResponseStatus(HttpStatus.OK)
    public void updateInventory(@RequestBody @Valid InventoryViewModel ivm){

    }
}
