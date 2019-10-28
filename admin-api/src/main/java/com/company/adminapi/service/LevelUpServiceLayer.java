package com.company.adminapi.service;

import com.company.adminapi.model.LevelUpViewModel;
import com.company.adminapi.util.feign.LevelUpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LevelUpServiceLayer {

    @Autowired
    LevelUpClient client;

    public Optional<LevelUpViewModel> findLevelUp(int id) {

        return client.getLevelUp(id);
    }

    public List<LevelUpViewModel> findAllLevelUps() {

        return client.getAllLevelUps();
    }

    public LevelUpViewModel createLevelUp(LevelUpViewModel luvm) {

        return client.addLevelUp(luvm);
    }

    public void updateLevelUp(LevelUpViewModel luvm) {
        client.updateLevelUp(luvm);
    }

    public void deleteLevelUp(int id) {
        client.deleteLevelUp(id);
    }

}
