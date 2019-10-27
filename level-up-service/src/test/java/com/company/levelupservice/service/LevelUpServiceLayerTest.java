package com.company.levelupservice.service;

import com.company.levelupservice.dao.LevelUpDao;
import com.company.levelupservice.dto.LevelUp;
import com.company.levelupservice.viewmodel.LevelUpViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class LevelUpServiceLayerTest {

    private LevelUpDao levelUpDao;
    private LevelUpServiceLayer serviceLayer;

    void setUpLevelUpDaoMock(){
        levelUpDao = mock(LevelUpDao.class);
        LevelUp levelUp = new LevelUp(1,50, LocalDate.of(2019,7,22));
        levelUp.setLevelUpId(1);

        LevelUp levelUp1 = new LevelUp(1,50, LocalDate.of(2019,7,22));

        List<LevelUp> levelUps = new ArrayList<>();
        levelUps.add(levelUp);

        doReturn(levelUp).when(levelUpDao).createLevelUp(levelUp1);
        doReturn(levelUp).when(levelUpDao).getLevelUp(1);
        doReturn(levelUps).when(levelUpDao).getAllLevelUps();
        doReturn(levelUp).when(levelUpDao).getLevelUpByCustomerId(1);
    }

    @BeforeEach
    void setUp() {
        setUpLevelUpDaoMock();
        serviceLayer = new LevelUpServiceLayer(levelUpDao);
    }

    @Test
    void createLevelUp() {
        LevelUpViewModel levelUp = new LevelUpViewModel(1,50, LocalDate.of(2019,7,22));
        levelUp = serviceLayer.createLevelUp(levelUp);

        assertEquals(levelUp, serviceLayer.findLevelUp(levelUp.getLevelUpId()));
    }

    @Test
    void findLevelUp() {
        LevelUpViewModel levelUp = new LevelUpViewModel(1,50, LocalDate.of(2019,7,22));
        levelUp = serviceLayer.createLevelUp(levelUp);

        assertEquals(levelUp, serviceLayer.findLevelUp(levelUp.getLevelUpId()));
    }

    @Test
    void findAllLevelUps() {
        LevelUpViewModel levelUp = new LevelUpViewModel(1,50, LocalDate.of(2019,7,22));
        serviceLayer.createLevelUp(levelUp);

        assertEquals(1, serviceLayer.findAllLevelUps().size());
    }

    @Test
    void updateLevelUp() {
        LevelUpViewModel levelUp = new LevelUpViewModel(1,50, LocalDate.of(2019,7,22));
        levelUp = serviceLayer.createLevelUp(levelUp);

        ArgumentCaptor<LevelUp> lvlCaptor = ArgumentCaptor.forClass(LevelUp.class);
        serviceLayer.updateLevelUp(levelUp);

        verify(levelUpDao, times(1)).updateLevelUp(lvlCaptor.capture());
    }

    @Test
    void deleteLevelUp() {
        LevelUpViewModel levelUp = new LevelUpViewModel(1,50, LocalDate.of(2019,7,22));
        levelUp = serviceLayer.createLevelUp(levelUp);

        ArgumentCaptor<Integer> intCaptor = ArgumentCaptor.forClass(Integer.class);
        serviceLayer.deleteLevelUp(levelUp.getLevelUpId());

        verify(levelUpDao, times(1)).deleteLevelUp(intCaptor.capture());
    }

    @Test
    void getLevelUpByCustomerId(){
        LevelUpViewModel levelUp = new LevelUpViewModel(1,50, LocalDate.of(2019,7,22));
        levelUp = serviceLayer.createLevelUp(levelUp);

        assertEquals(levelUp, serviceLayer.getLevelUpByCustomerId(1));
    }
}