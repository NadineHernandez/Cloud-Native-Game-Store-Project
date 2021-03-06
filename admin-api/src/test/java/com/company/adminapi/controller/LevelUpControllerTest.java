package com.company.adminapi.controller;

import com.company.adminapi.model.LevelUpViewModel;
import com.company.adminapi.service.LevelUpServiceLayer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(LevelUpController.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
class LevelUpControllerTest {

    @MockBean
    DataSource dataSource;

    @MockBean
    LevelUpServiceLayer service;

    @Autowired
    MockMvc mockMvc;

    ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    @Test
    @WithMockUser(username="adminUser", roles = {"ADMIN"})
    void getLevelUp() throws Exception{
        LevelUpViewModel luvm = new LevelUpViewModel(10001, 101,50, LocalDate.of(2019,7,22));
        when(service.findLevelUp(luvm.getLevelUpId())).thenReturn(Optional.of(luvm));
        String outputJson = mapper.writeValueAsString(luvm);
        mockMvc.perform(get("/levelup/10001")
                .with(csrf().asHeader()))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    @WithMockUser(username="adminUser", roles = {"ADMIN"})
    void getAllLevelUps() throws Exception{
        LevelUpViewModel luvm = new LevelUpViewModel(10001, 101,50, LocalDate.of(2019,7,22));
        List<LevelUpViewModel> luvms = Collections.singletonList(luvm);
        when(service.findAllLevelUps()).thenReturn(luvms);
        String outputJson = mapper.writeValueAsString(luvms);
        mockMvc.perform(get("/levelup")
                .with(csrf().asHeader()))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    @WithMockUser(username="adminUser", roles = {"ADMIN"})
    void addLevelUp() throws Exception{
        LevelUpViewModel luvm = new LevelUpViewModel(10001, 101,50, LocalDate.of(2019,7,22));
        LevelUpViewModel luvm2 = new LevelUpViewModel(101,50, LocalDate.of(2019,7,22));

        when(service.createLevelUp(luvm2)).thenReturn(luvm);
        String inputJson = mapper.writeValueAsString(luvm2);
        String outputJson = mapper.writeValueAsString(luvm);
        mockMvc.perform(post("/levelup")
                .with(csrf().asHeader())
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    @WithMockUser(username="adminUser", roles = {"ADMIN"})
    void updateLevelUp() throws Exception{
        LevelUpViewModel luvm = new LevelUpViewModel(10001, 101,50, LocalDate.of(2019,7,22));
        String inputJson = mapper.writeValueAsString(luvm);
        mockMvc.perform(put("/levelup")
                .with(csrf().asHeader())
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(username="adminUser", roles = {"ADMIN"})
    void deleteLevelUp() throws Exception{
        mockMvc.perform(delete("/levelup/1")
                .with(csrf().asHeader()))
                .andExpect(status().isNoContent());
    }

    // copy of tests with no auth

    @Test
    void getLevelUpNoAuth() throws Exception{
        LevelUpViewModel luvm = new LevelUpViewModel(10001, 101,50, LocalDate.of(2019,7,22));
        when(service.findLevelUp(luvm.getLevelUpId())).thenReturn(Optional.of(luvm));
        String outputJson = mapper.writeValueAsString(luvm);
        mockMvc.perform(get("/levelup/10001"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void getAllLevelUpsNoAuth() throws Exception{
        LevelUpViewModel luvm = new LevelUpViewModel(10001, 101,50, LocalDate.of(2019,7,22));
        List<LevelUpViewModel> luvms = Collections.singletonList(luvm);
        when(service.findAllLevelUps()).thenReturn(luvms);
        String outputJson = mapper.writeValueAsString(luvms);
        mockMvc.perform(get("/levelup"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void addLevelUpNoAuth() throws Exception{
        LevelUpViewModel luvm = new LevelUpViewModel(10001, 101,50, LocalDate.of(2019,7,22));
        LevelUpViewModel luvm2 = new LevelUpViewModel(101,50, LocalDate.of(2019,7,22));

        when(service.createLevelUp(luvm2)).thenReturn(luvm);
        String inputJson = mapper.writeValueAsString(luvm2);
        String outputJson = mapper.writeValueAsString(luvm);
        mockMvc.perform(post("/levelup")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    void updateLevelUpNoAuth() throws Exception{
        LevelUpViewModel luvm = new LevelUpViewModel(10001, 101,50, LocalDate.of(2019,7,22));
        String inputJson = mapper.writeValueAsString(luvm);
        mockMvc.perform(put("/levelup")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    void deleteLevelUpNoAuth() throws Exception{
        mockMvc.perform(delete("/levelup/1"))
                .andExpect(status().isForbidden());
    }
}