package com.example.springbootstartertestmvc.mapper;

import com.example.springbootstartertestmvc.dto.PersonReadDto;
import com.example.springbootstartertestmvc.entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Zhurenkov Pavel 24.03.2023
 */

@Component
@RequiredArgsConstructor
public class PersonReadMapper implements Mapper<Person, PersonReadDto> {

    @Override
    public PersonReadDto map(Person object) {
        return new PersonReadDto(
                object.getId(),
                object.getName()
        );
    }
}
