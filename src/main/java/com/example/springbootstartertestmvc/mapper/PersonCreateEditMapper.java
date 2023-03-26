package com.example.springbootstartertestmvc.mapper;

import com.example.springbootstartertestmvc.dto.PersonCreateEditDto;
import com.example.springbootstartertestmvc.entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Zhurenkov Pavel 24.03.2023
 */

@Component
@RequiredArgsConstructor
public class PersonCreateEditMapper implements Mapper<PersonCreateEditDto, Person>{

    @Override
    public Person map(PersonCreateEditDto fromObject, Person toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    @Override
    public Person map(PersonCreateEditDto object) {
        Person person = new Person();
        copy(object, person);
        return person;
    }

    private void copy(PersonCreateEditDto object, Person person) {
        person.setName(object.getName());

    }
}
