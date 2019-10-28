package com.company.adminapi.controller;

import com.company.adminapi.model.ProductViewModel;
import com.company.adminapi.service.ProductServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {


    @Autowired
    ProductServiceLayer service;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductViewModel getProduct(@PathVariable int id) {
        return service.findProduct(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductViewModel> getAllProducts() {
        return service.findAllProducts();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductViewModel addProduct(@RequestBody @Valid ProductViewModel cvm) {
        return service.createProduct(cvm);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduct(@RequestBody @Valid ProductViewModel cvm) {
        service.updateProduct(cvm);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable int id) {
        service.deleteProduct(id);
    }
}
