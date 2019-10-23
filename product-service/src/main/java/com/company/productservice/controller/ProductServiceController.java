package com.company.productservice.controller;

import com.company.productservice.model.ProductViewModel;
import com.company.productservice.service.ProductServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductServiceController {

    @Autowired
    ProductServiceLayer service;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductViewModel getProduct(@PathVariable int id) {

        return service.findProduct(id).orElseThrow(()-> new HttpClientErrorException(HttpStatus.NOT_FOUND,
                "product with id "+ id + " not found"));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductViewModel> getAllProducts() {

        return service.findAllProducts() ;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductViewModel addProduct(@RequestBody @Valid ProductViewModel pvm) {

        return service.createProduct(pvm);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduct(@RequestBody @Valid ProductViewModel pvm) {
        service.updateProduct(pvm);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable int id) {
        service.deleteProduct(id);
    }


}
