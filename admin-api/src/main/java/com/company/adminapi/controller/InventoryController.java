package com.company.adminapi.controller;

import com.company.adminapi.model.InventoryViewModel;
import com.company.adminapi.service.InventoryServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    InventoryServiceLayer service;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InventoryViewModel getInventory(@PathVariable int id) {
        return service.findInventory(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryViewModel> getAllInventories() {
        return service.findAllInventories();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InventoryViewModel addInventory(@RequestBody @Valid InventoryViewModel cvm) {
        return service.createInventory(cvm);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateInventory(@RequestBody @Valid InventoryViewModel cvm) {
        service.updateInventory(cvm);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)

    public void deleteInventory(@PathVariable int id) {
        service.deleteInventory(id);
    }
}
