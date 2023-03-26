package com.example.springbootstartertestmvc.controller;

import com.example.springbootstartertestmvc.dto.PersonReadDto;
import com.example.springbootstartertestmvc.entity.Person;
import com.example.springbootstartertestmvc.repository.PersonRepository;
import com.example.springbootstartertestmvc.service.PersonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * @author Zhurenkov Pavel 25.03.2023
 */

@WebMvcTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class PersonRestControllerTest {

    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;
    @MockBean
    private final PersonRepository personRepository;
    @MockBean
    private final PersonService personService;

    @Test
    void findAll() throws Exception {
        PersonReadDto person = new PersonReadDto(1l, "Michail");
        Mockito.when(personService.findAll()).thenReturn(Collections.singletonList(person));

        mockMvc.perform(get("/ipi/persons"))
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(person)));
    }

    @Test
    void findById() {
    }

    @Test
    void findByName() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}
