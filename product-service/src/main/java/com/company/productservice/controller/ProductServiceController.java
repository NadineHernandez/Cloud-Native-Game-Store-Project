package com.company.productservice.controller;

import com.company.productservice.model.ProductViewModel;
import com.company.productservice.service.ProductServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductServiceController {

    @Autowired
    ProductServiceLayer service;

    @GetMapping("/{id}")
    public ProductViewModel getProduct(@PathVariable int id) {
        return null;
    }

    @GetMapping
    public List<ProductViewModel> getAllProducts() {
        return null;
    }

    @PostMapping
    public ProductViewModel addProduct(@RequestBody @Valid ProductViewModel pvm) {
        return null;
    }

    @PutMapping
    public void updateProduct(@RequestBody @Valid ProductViewModel pvm) {

    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id) {

    }


}
