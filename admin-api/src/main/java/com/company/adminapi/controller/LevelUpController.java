package com.company.adminapi.controller;

import com.company.adminapi.model.LevelUpViewModel;
import com.company.adminapi.service.LevelUpServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/levelup")
public class LevelUpController {
    @Autowired
    LevelUpServiceLayer service;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LevelUpViewModel getLevelUp(@PathVariable int id) {
        return service.findLevelUp(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<LevelUpViewModel> getAllLevelUps() {
        return service.findAllLevelUps();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LevelUpViewModel addLevelUp(@RequestBody @Valid LevelUpViewModel cvm) {
        return service.createLevelUp(cvm);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateLevelUp(@RequestBody @Valid LevelUpViewModel cvm) {
        service.updateLevelUp(cvm);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLevelUp(@PathVariable int id) {
        service.deleteLevelUp(id);
    }

}
