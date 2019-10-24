package com.company.adminapi.util.feign;

import com.company.adminapi.model.LevelUpViewModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FeignClient(name="level-up-service", decode404=true)
public interface LevelUpClient {

    @GetMapping("/levelup/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<LevelUpViewModel> getLevelUp(@PathVariable int id);

    @GetMapping("/levelup")
    @ResponseStatus(HttpStatus.OK)
    public List<LevelUpViewModel> getAllLevelUps();

    @PostMapping("/levelup")
    @ResponseStatus(HttpStatus.CREATED)
    public LevelUpViewModel addLevelUp(@RequestBody LevelUpViewModel luvm);

    @PutMapping("/levelup")
    @ResponseStatus(HttpStatus.OK)
    public void updateLevelUp(@RequestBody LevelUpViewModel luvm);

    @DeleteMapping({"/levelup/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteLevelUp(@PathVariable int id);

}
