package com.company.adminapi.util.feign;

import com.company.adminapi.model.InventoryViewModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@FeignClient(name="inventory-service", decode404 = true)
public interface InventoryClient {

    @PostMapping(value = "/inventory")
    @ResponseStatus(HttpStatus.CREATED)
    public InventoryViewModel addInventory(@RequestBody InventoryViewModel ivm);

    @GetMapping(value = "/inventory")
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryViewModel> getAllInventories();

    @GetMapping(value = "/inventory/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<InventoryViewModel> getInventory(@PathVariable int id);

    @PutMapping(value = "/inventory")
    @ResponseStatus(HttpStatus.OK)
    public void updateInventory(@RequestBody @Valid InventoryViewModel ivm);

    @DeleteMapping(value = "/inventory/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteInventory(@PathVariable int id);
}
