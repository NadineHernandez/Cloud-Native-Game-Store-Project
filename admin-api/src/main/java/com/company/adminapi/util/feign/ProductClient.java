package com.company.adminapi.util.feign;

import com.company.adminapi.model.ProductViewModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@FeignClient(name="product-service", decode404=true)
public interface ProductClient {

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<ProductViewModel> getProduct(@PathVariable int id);

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
