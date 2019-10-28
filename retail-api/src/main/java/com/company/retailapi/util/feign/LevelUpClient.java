package com.company.retailapi.util.feign;

import com.company.retailapi.models.LevelUpViewModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "levelup-service")
public interface LevelUpClient {
    @PostMapping(value = "/levelup")
    @ResponseStatus(HttpStatus.CREATED)
    public LevelUpViewModel addLevelUp(@RequestBody @Valid LevelUpViewModel luvm);

    @GetMapping(value = "/levelup")
    @ResponseStatus(HttpStatus.OK)
    public List<LevelUpViewModel> getAllLevelUps();

    @GetMapping(value = "/levelup/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LevelUpViewModel getLevelUp(@PathVariable int id);

    @PutMapping(value = "/levelup")
    @ResponseStatus(HttpStatus.OK)
    public void updateLevelUp(@RequestBody @Valid LevelUpViewModel luvm);

    @DeleteMapping(value = "/levelup/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteLevelUp(@PathVariable int id);

    @GetMapping(value = "/levelup/customer/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public LevelUpViewModel getLevelUpByCustomerId(@PathVariable int customerId);
}
