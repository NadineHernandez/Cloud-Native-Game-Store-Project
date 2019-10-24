package com.company.levelupservice.dao;

import com.company.levelupservice.dto.LevelUp;

import java.util.List;

public interface LevelUpDao {
    LevelUp createLevelUp(LevelUp levelUp);
    LevelUp getLevelUp(int id);
    List<LevelUp> getAllLevelUps();
    void updateLevelUp(LevelUp levelUp);
    void deleteLevelUp(int id);
}
