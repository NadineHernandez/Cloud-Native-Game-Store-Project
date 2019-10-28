package com.company.retailapi.util.feign;

import com.company.retailapi.models.InventoryViewModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "inventory-service")
public interface InventoryClient {
    @PostMapping(value = "/inventory")
    @ResponseStatus(HttpStatus.CREATED)
    public InventoryViewModel addInventory(@RequestBody @Valid InventoryViewModel ivm);

    @GetMapping(value = "/inventory")
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryViewModel> getAllInventories();

    @GetMapping(value = "/inventory/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InventoryViewModel getInventory(@PathVariable int id);

    @PutMapping(value = "/inventory")
    @ResponseStatus(HttpStatus.OK)
    public void updateInventory(@RequestBody @Valid InventoryViewModel ivm);

    @DeleteMapping(value = "/inventory/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteInvoice(@PathVariable int id);
}
