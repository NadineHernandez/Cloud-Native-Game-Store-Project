package com.company.levelupservice.dao;

import com.company.levelupservice.dto.LevelUp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LevelUpDaoTest {

    @Autowired
    private LevelUpDao lvlUpDao;

    @BeforeEach
    void setUp() {
        lvlUpDao.getAllLevelUps().stream().forEach(levelUp -> {
            lvlUpDao.deleteLevelUp(levelUp.getLevelUpId());
        });
    }

    @Test
    void createLevelUp() {
        LevelUp levelUp = new LevelUp(1,50, LocalDate.of(2019,7,22));
        levelUp = lvlUpDao.createLevelUp(levelUp);

        assertEquals(levelUp, lvlUpDao.getLevelUp(levelUp.getLevelUpId()));
    }

    @Test
    void getLevelUp() {
        LevelUp levelUp = new LevelUp(1,50, LocalDate.of(2019,7,22));
        levelUp = lvlUpDao.createLevelUp(levelUp);

        assertEquals(levelUp, lvlUpDao.getLevelUp(levelUp.getLevelUpId()));
    }

    @Test
    void getAllLevelUp() {
        LevelUp levelUp = new LevelUp(1,50, LocalDate.of(2019,7,22));
        lvlUpDao.createLevelUp(levelUp);

        assertEquals(1, lvlUpDao.getAllLevelUps().size());
    }

    @Test
    void updateLevelUp() {
        LevelUp levelUp = new LevelUp(1,50, LocalDate.of(2019,7,22));
        levelUp = lvlUpDao.createLevelUp(levelUp);

        levelUp.setCustomerId(2);
        levelUp.setPoints(100);
        levelUp.setMemberDate(LocalDate.of(2019,8,23));
        lvlUpDao.updateLevelUp(levelUp);

        assertEquals(levelUp, lvlUpDao.getLevelUp(levelUp.getLevelUpId()));
    }

    @Test
    void deleteLevelUp() {
        LevelUp levelUp = new LevelUp(1,50, LocalDate.of(2019,7,22));
        levelUp = lvlUpDao.createLevelUp(levelUp);
        assertEquals(levelUp, lvlUpDao.getLevelUp(levelUp.getLevelUpId()));

        lvlUpDao.deleteLevelUp(levelUp.getLevelUpId());
        assertNull(lvlUpDao.getLevelUp(levelUp.getLevelUpId()));
    }

    @Test
    void getLevelUpByCustomerId(){
        LevelUp levelUp = new LevelUp(1,50, LocalDate.of(2019,7,22));
        levelUp = lvlUpDao.createLevelUp(levelUp);

        assertEquals(levelUp, lvlUpDao.getLevelUpByCustomerId(levelUp.getCustomerId()));
    }
}