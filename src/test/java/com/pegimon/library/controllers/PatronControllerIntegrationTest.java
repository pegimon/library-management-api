package com.pegimon.library.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pegimon.library.TestingUtils;
import com.pegimon.library.models.Entities.PatronEntity;
import com.pegimon.library.models.dto.PatronDto;
import com.pegimon.library.services.PatronService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class PatronControllerIntegrationTest {
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final PatronService patronService;

    @Autowired
    public PatronControllerIntegrationTest(MockMvc mockMvc, ObjectMapper objectMapper, PatronService patronService) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.patronService = patronService;
    }

    @Test
    public void testCreatePatronReturns201() throws Exception {
        PatronDto testPatronA = TestingUtils.createPatronDto();
        String patronJson = objectMapper.writeValueAsString(testPatronA);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/patrons")
                        .contentType("application/json")
                        .content(patronJson)
        ).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testThatUpdatePatronReturns200() throws Exception {
        PatronEntity testPatronA = TestingUtils.createPatronEntityA();
        patronService.addPatron(testPatronA);
        testPatronA.setName("Updated Name");
        String patronJson = objectMapper.writeValueAsString(testPatronA);
        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/patrons/" + testPatronA.getId())
                        .contentType("application/json")
                        .content(patronJson)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatDeletePatronReturns200() throws Exception {
        PatronEntity testPatronA = TestingUtils.createPatronEntityA();
        patronService.addPatron(testPatronA);
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/patrons/" + testPatronA.getId())
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatGetAllPatronsReturns200() throws Exception {
        PatronEntity testPatronA = TestingUtils.createPatronEntityA();
        patronService.addPatron(testPatronA);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/patrons")
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatGetPatronByIdReturns200() throws Exception {
        PatronEntity testPatronA = TestingUtils.createPatronEntityA();
        patronService.addPatron(testPatronA);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/patrons/" + testPatronA.getId())
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatGetPatronByIdReturns404() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/patrons/1")
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testCreatePatronReturnsPatron() throws Exception {
        PatronEntity testPatronA = TestingUtils.createPatronEntityA();
        String patronJson = objectMapper.writeValueAsString(testPatronA);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/patrons")
                        .contentType("application/json")
                        .content(patronJson)
        ).andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(testPatronA.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(testPatronA.getEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value(testPatronA.getPhoneNumber()));
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/patrons/" + testPatronA.getId())
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(patronJson));
    }

    @Test
    public void testThatUpdatePatronReturnsPatron() throws Exception {
        PatronEntity testPatronA = TestingUtils.createPatronEntityA();
        patronService.addPatron(testPatronA);
        testPatronA.setName("Updated Name");
        String patronJson = objectMapper.writeValueAsString(testPatronA);
        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/patrons/" + testPatronA.getId())
                        .contentType("application/json")
                        .content(patronJson)
        ).andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(testPatronA.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(testPatronA.getEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value(testPatronA.getPhoneNumber()));
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/patrons/" + testPatronA.getId())
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(patronJson));
    }

    @Test
    public void testThatDeletePatronReturnsPatron() throws Exception {
        PatronEntity testPatronA = TestingUtils.createPatronEntityA();
        patronService.addPatron(testPatronA);
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/patrons/" + testPatronA.getId())
        ).andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(testPatronA.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(testPatronA.getEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value(testPatronA.getPhoneNumber()));
    }

    @Test
    public void testThatGetAllPatronsReturnsPatrons() throws Exception {
        PatronEntity testPatronA = TestingUtils.createPatronEntityA(), testPatronB = TestingUtils.createPatronEntityB(), testPatronC = TestingUtils.createPatronEntityC();
        patronService.addPatron(testPatronA);
        patronService.addPatron(testPatronB);
        patronService.addPatron(testPatronC);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/patrons")
        ).andExpect(MockMvcResultMatchers.jsonPath("$[0].id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(testPatronA.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value(testPatronA.getEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].phoneNumber").value(testPatronA.getPhoneNumber()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value(testPatronB.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].email").value(testPatronB.getEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].phoneNumber").value(testPatronB.getPhoneNumber()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].name").value(testPatronC.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].email").value(testPatronC.getEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].phoneNumber").value(testPatronC.getPhoneNumber()));
    }

    @Test
    public void testThatGetPatronByIdReturnsPatron() throws Exception {
        PatronEntity testPatronA = TestingUtils.createPatronEntityA();
        patronService.addPatron(testPatronA);
        String patronJson = objectMapper.writeValueAsString(testPatronA);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/patrons/" + testPatronA.getId())
        ).andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(testPatronA.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(testPatronA.getEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value(testPatronA.getPhoneNumber()))
                .andExpect(MockMvcResultMatchers.content().json(patronJson));
    }
}
