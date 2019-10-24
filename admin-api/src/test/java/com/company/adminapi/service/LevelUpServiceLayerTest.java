package com.company.adminapi.service;

import com.company.adminapi.util.feign.LevelUpClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LevelUpServiceLayerTest {

    @InjectMocks
    LevelUpServiceLayer service;

    @Mock
    LevelUpClient client;

    @Test
    void findLevelUp() {

        
    }

    @Test
    void findAllLevelUps() {
    }

    @Test
    void createLevelUp() {
    }

    @Test
    void updateLevelUp() {
    }

    @Test
    void deleteLevelUp() {
    }
}