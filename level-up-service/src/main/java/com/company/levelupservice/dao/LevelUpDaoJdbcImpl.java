package com.company.levelupservice.dao;

import com.company.levelupservice.dto.LevelUp;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LevelUpDaoJdbcImpl implements LevelUpDao{
    @Override
    public LevelUp createLevelUp(LevelUp levelUp) {
        return null;
    }

    @Override
    public LevelUp getLevelUp(int id) {
        return null;
    }

    @Override
    public List<LevelUp> getAllLevelUp() {
        return null;
    }

    @Override
    public void updateLevelUp(LevelUp levelUp) {

    }

    @Override
    public void deleteLevelUp(int id) {

    }
}
