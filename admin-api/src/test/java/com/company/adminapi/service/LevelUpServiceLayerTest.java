package com.company.adminapi.service;

import com.company.adminapi.model.LevelUpViewModel;
import com.company.adminapi.util.feign.LevelUpClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class LevelUpServiceLayerTest {

    @InjectMocks
    LevelUpServiceLayer service;

    @Mock
    LevelUpClient client;

    @Test
    void findLevelUp() {
        LevelUpViewModel luvm = new LevelUpViewModel(10001, 101,50, LocalDate.of(2019,7,22));
        when(client.getLevelUp(luvm.getLevelUpId())).thenReturn(Optional.of(luvm));
        assertEquals(Optional.of(luvm), service.findLevelUp(luvm.getLevelUpId()));


    }

    @Test
    void findAllLevelUps() {
        LevelUpViewModel luvm = new LevelUpViewModel(10001, 101,50, LocalDate.of(2019,7,22));
        List<LevelUpViewModel> luvms = Collections.singletonList(luvm);
        when(client.getAllLevelUps()).thenReturn(luvms);
        assertEquals(luvms, service.findAllLevelUps());
    }

    @Test
    void createLevelUp() {
        LevelUpViewModel luvm = new LevelUpViewModel(10001, 101,50, LocalDate.of(2019,7,22));
        LevelUpViewModel luvm2 = new LevelUpViewModel(101,50, LocalDate.of(2019,7,22));
        when(client.addLevelUp(luvm2)).thenReturn(luvm);
        LevelUpViewModel fromService = service.createLevelUp(luvm2);
        assertEquals(fromService, luvm);
    }

    @Test
    void updateLevelUp() {
        LevelUpViewModel luvm = new LevelUpViewModel(10001, 101,50, LocalDate.of(2019,7,22));
        service.updateLevelUp(luvm);
        verify(client, times(1)).updateLevelUp(luvm);
    }

    @Test
    void deleteLevelUp() {
        service.deleteLevelUp(10001);
        verify(client, times(1)).deleteLevelUp(10001);
    }
}