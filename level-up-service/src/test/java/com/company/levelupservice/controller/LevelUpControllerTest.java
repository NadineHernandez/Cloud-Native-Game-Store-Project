package com.company.levelupservice.controller;

import com.company.levelupservice.service.LevelUpServiceLayer;
import com.company.levelupservice.viewmodel.LevelUpViewModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = LevelUpController.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
class LevelUpControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LevelUpServiceLayer serviceLayer;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    void addLevelUp() throws Exception{
        LevelUpViewModel inputLvl = new LevelUpViewModel(1,50, LocalDate.of(2019,7,22));
        String inputJson = mapper.writeValueAsString(inputLvl);

        LevelUpViewModel outputLvl = new LevelUpViewModel(1,50, LocalDate.of(2019,7,22));
        outputLvl.setLevelUpId(1);
        String outputJson = mapper.writeValueAsString(outputLvl);

        when(serviceLayer.createLevelUp(inputLvl)).thenReturn(outputLvl);

        this.mockMvc.perform(post("/levelup")
        .content(inputJson)
        .contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    void getAllLevelUps() throws Exception{
        LevelUpViewModel outputLvl = new LevelUpViewModel(1,50, LocalDate.of(2019,7,22));
        outputLvl.setLevelUpId(1);

        List<LevelUpViewModel> luvms = new ArrayList<>();
        luvms.add(outputLvl);

        when(serviceLayer.findAllLevelUps()).thenReturn(luvms);

        List<LevelUpViewModel> listChecker = new ArrayList<>();
        listChecker.addAll(luvms);

        String outputJson = mapper.writeValueAsString(listChecker);

        this.mockMvc.perform(get("/levelup"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    void getLevelUp() throws Exception{
        LevelUpViewModel outputLvl = new LevelUpViewModel(1,50, LocalDate.of(2019,7,22));
        outputLvl.setLevelUpId(1);
        String outputJson = mapper.writeValueAsString(outputLvl);

        when(serviceLayer.findLevelUp(1)).thenReturn(outputLvl);

        this.mockMvc.perform(get("/levelup/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    void getLevelUpThatDoesNotExistShouldReturn404() throws Exception{
        int id = 90000;

        when(serviceLayer.findLevelUp(id)).thenReturn(null);

        this.mockMvc.perform(get("/levelup/" + id))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void updateLevelUp() throws Exception{
        LevelUpViewModel inputLvl = new LevelUpViewModel(1,50, LocalDate.of(2019,7,22));
        inputLvl.setLevelUpId(1);
        String inputJson = mapper.writeValueAsString(inputLvl);

        this.mockMvc.perform(put("/levelup")
        .content(inputJson)
        .contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void deleteLevelUp() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/levelup/1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(""));
    }
}