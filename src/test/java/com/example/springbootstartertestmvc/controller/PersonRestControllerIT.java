package com.example.springbootstartertestmvc.controller;

import com.example.springbootstartertestmvc.entity.Person;
import com.example.springbootstartertestmvc.repository.PersonRepository;
import com.example.springbootstartertestmvc.service.IntegrationTestBase;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Zhurenkov Pavel 25.03.2023
 */

@AutoConfigureMockMvc
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class PersonRestControllerIT extends IntegrationTestBase {

    private final MockMvc mockMvc; // заменяет диспатчер сервлет, без запуска http сервера

    private final ObjectMapper objectMapper;

    private final PersonRepository personRepository;

    @AfterEach
    void resetDb(){
        personRepository.deleteAll();
    }

    @Test
    void findAll() throws Exception {
        Person p1 = Person.builder().id(1l).name("Olga").build();
        Person p2 = Person.builder().id(2l).name("Petr").build();
        Person p3 = Person.builder().id(3l).name("Sveta").build();
        Person p4 = Person.builder().id(4l).name("Vlad").build();
        Person p5 = Person.builder().id(5l).name("Kate").build();

        mockMvc.perform(post("/api/persons")
                .content(objectMapper.writeValueAsString("Olga"))
                .contentType(MediaType.APPLICATION_JSON)
        );
        mockMvc.perform(post("/api/persons")
                .content(objectMapper.writeValueAsString("Petr"))
                .contentType(MediaType.APPLICATION_JSON)
        );
        mockMvc.perform(post("/api/persons")
                .content(objectMapper.writeValueAsString("Sveta"))
                .contentType(MediaType.APPLICATION_JSON)
        );
        mockMvc.perform(post("/api/persons")
                .content(objectMapper.writeValueAsString("Vlad"))
                .contentType(MediaType.APPLICATION_JSON)
        );
        mockMvc.perform(post("/api/persons")
                .content(objectMapper.writeValueAsString("Kate"))
                .contentType(MediaType.APPLICATION_JSON)
        );

        mockMvc.perform(get("/api/persons"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(
                        Arrays.asList(p1, p2, p3, p4, p5)
                )));
    }

    @Test
    void create_and_find_all_persons() throws Exception {
        Person p1 = Person.builder().id(6l).name("Ivan").build();
        Person p2 = Person.builder().id(7l).name("Olga").build();

        mockMvc.perform(post("/api/persons")
                .content(objectMapper.writeValueAsString("Ivan"))
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(6l))
                .andExpect(jsonPath("$.name").value("Ivan"));

        mockMvc.perform(post("/api/persons")
                        .content(objectMapper.writeValueAsString("Olga"))
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(7l))
                .andExpect(jsonPath("$.name").value("Olga"));

        mockMvc.perform(get("/api/persons"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(
                        Arrays.asList(p1, p2)
                )));
    }

    @Test
    void deleteTrue() throws Exception {
        mockMvc.perform(post("/api/persons")
                        .content(objectMapper.writeValueAsString("Ivan"))
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(8l))
                .andExpect(jsonPath("$.name").value("Ivan"));

        Long id = 8l;
        mockMvc.perform(delete("/api/persons/{id}", id))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteFalse() throws Exception {
        Long id = 9l;
        mockMvc.perform(delete("/api/persons/{id}", id))
                .andExpect(status().isNotFound());
    }

}
