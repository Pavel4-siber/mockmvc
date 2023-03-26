package com.example.springbootstartertestmvc.service;

import com.example.springbootstartertestmvc.dto.PersonCreateEditDto;
import com.example.springbootstartertestmvc.dto.PersonReadDto;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author Zhurenkov Pavel 25.03.2023
 */

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class PersonServiceIT extends IntegrationTestBase{

    private static final Long PERSON_1 = 2L;

    private final PersonService personService;

    @Test
    void create() {
        PersonCreateEditDto personDto1 = new PersonCreateEditDto("Ivan");
        PersonReadDto actualResult1 = personService.create(personDto1);
        assertEquals(personDto1.getName(), actualResult1.getName());
        assertEquals(1L, actualResult1.getId());

        PersonCreateEditDto personDto2 = new PersonCreateEditDto("Fed");
        PersonReadDto actualResult2 = personService.create(personDto2);
        assertEquals(personDto2.getName(), actualResult2.getName());
        assertEquals(2L, actualResult2.getId());
    }

    @Test
    void findAll(){
        List<PersonReadDto> result = personService.findAll();
        Assertions.assertThat(result).hasSize(5);
    }

    @Test
    void update(){
        PersonCreateEditDto personDto = new PersonCreateEditDto("Ivan");
        PersonReadDto actRes = personService.findById(PERSON_1).get();
        assertEquals("Olga", actRes.getName());

        PersonReadDto actualResult = personService.update(PERSON_1, personDto).get();
        assertEquals(personDto.getName(),actualResult.getName());
    }

    @Test
    void delete(){
        boolean actualResult = personService.delete(PERSON_1);
        assertTrue(actualResult);
    }

    @Test
    void deleteFailed(){
        boolean actualResult = personService.delete(1L);
        assertFalse(actualResult);
    }

    @Test
    void findById() {
        PersonReadDto actualResult = personService.findById(PERSON_1).get();
        assertEquals("Olga",actualResult.getName());
    }

    @Test
    void findByName() {
        Optional<PersonReadDto> actualResult = personService.findByName("Olga");
        Assertions.assertThat(actualResult).isPresent();
        assertEquals(2L, actualResult.get().getId());
        //verify();
    }
}
