package com.company.adminapi.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@WebMvcTest(LevelUpController.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
class LevelUpControllerTest {

    @Test
    void getLevelUp() {
    }

    @Test
    void getAllLevelUps() {
    }

    @Test
    void addLevelUp() {
    }

    @Test
    void updateLevelUp() {
    }

    @Test
    void deleteLevelUp() {
    }
}