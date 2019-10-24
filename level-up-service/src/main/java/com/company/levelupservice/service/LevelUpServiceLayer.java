package com.company.levelupservice.service;

import com.company.levelupservice.dao.LevelUpDao;
import com.company.levelupservice.dto.LevelUp;
import com.company.levelupservice.viewmodel.LevelUpViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LevelUpServiceLayer {
    @Autowired
    private LevelUpDao lvlDao;

    public LevelUpServiceLayer(LevelUpDao lvlDao) {
        this.lvlDao = lvlDao;
    }

    private LevelUpViewModel buildLevelUpViewModel(LevelUp levelUp){
        LevelUpViewModel luvm = new LevelUpViewModel(levelUp.getCustomerId(), levelUp.getPoints(), levelUp.getMemberDate());
        luvm.setLevelUpId(levelUp.getLevelUpId());
        return luvm;
    }

    public LevelUpViewModel createLevelUp(LevelUpViewModel luvm){
        LevelUp levelUp = new LevelUp(luvm.getCustomerId(), luvm.getPoints(), luvm.getMemberDate());
        levelUp = lvlDao.createLevelUp(levelUp);
        return buildLevelUpViewModel(levelUp);
    }

    public LevelUpViewModel findLevelUp(int id){
        LevelUp levelUp = lvlDao.getLevelUp(id);
        return buildLevelUpViewModel(levelUp);
    }

    public List<LevelUpViewModel> findAllLevelUps(){
        List<LevelUpViewModel> luvms = new ArrayList<>();
        lvlDao.getAllLevelUps().stream().forEach(levelUp -> {
            LevelUpViewModel luvm = buildLevelUpViewModel(levelUp);
            luvms.add(luvm);
        });
        return luvms;
    }

    public void updateLevelUp(LevelUpViewModel luvm){
        LevelUp levelUp = new LevelUp(luvm.getCustomerId(), luvm.getPoints(), luvm.getMemberDate());
        levelUp.setLevelUpId(luvm.getLevelUpId());
        lvlDao.updateLevelUp(levelUp);
    }

    public void deleteLevelUp(int id){
        lvlDao.deleteLevelUp(id);
    }
}
