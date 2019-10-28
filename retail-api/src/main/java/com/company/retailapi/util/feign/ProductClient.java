package com.company.retailapi.util.feign;

import com.company.retailapi.models.ProductViewModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "product-service")
@RequestMapping(value = "/product")
public interface ProductClient {
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductViewModel getProduct(@PathVariable int id);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductViewModel> getAllProducts();

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductViewModel addProduct(@RequestBody @Valid ProductViewModel pvm);

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduct(@RequestBody @Valid ProductViewModel pvm);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable int id);
}
