package com.company.levelupservice.controller;

import com.company.levelupservice.service.LevelUpServiceLayer;
import com.company.levelupservice.viewmodel.LevelUpViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RefreshScope
public class LevelUpController {

    @Autowired
    private LevelUpServiceLayer serviceLayer;

    @PostMapping(value = "/levelup")
    @ResponseStatus(HttpStatus.CREATED)
    public LevelUpViewModel addLevelUp(@RequestBody @Valid LevelUpViewModel luvm){
        return null;
    }

    @GetMapping(value = "/levelup")
    @ResponseStatus(HttpStatus.OK)
    public List<LevelUpViewModel> getAllLevelUps(){
        return null;
    }

    @GetMapping(value = "/levelup/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LevelUpViewModel getLevelUp(@PathVariable int id){
        return null;
    }

    @PutMapping(value = "/levelup")
    @ResponseStatus(HttpStatus.OK)
    public void updateLevelUp(@RequestBody @Valid LevelUpViewModel luvm){

    }

    @DeleteMapping(value = "/levelup/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteLevelUp(@PathVariable int id){

    }

}
