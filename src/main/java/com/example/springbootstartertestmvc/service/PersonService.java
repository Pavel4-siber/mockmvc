package com.example.springbootstartertestmvc.service;

import com.example.springbootstartertestmvc.dto.PersonCreateEditDto;
import com.example.springbootstartertestmvc.dto.PersonReadDto;
import com.example.springbootstartertestmvc.entity.Person;
import com.example.springbootstartertestmvc.mapper.PersonCreateEditMapper;
import com.example.springbootstartertestmvc.mapper.PersonReadMapper;
import com.example.springbootstartertestmvc.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Zhurenkov Pavel 24.03.2023
 */

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonReadMapper personReadMapper;
    private final PersonCreateEditMapper personCreateEditMapper;

    public List<PersonReadDto> findAll(){
        return personRepository.findAll().stream()
                .map(personReadMapper::map)
                .toList();
    }

    public Optional<PersonReadDto> findById(Long id){
        return personRepository.findById(id)
                .map(personReadMapper::map);
    }

    public Optional<PersonReadDto> findByName(String name) {
        return personRepository.findByName(name)
                .map(personReadMapper::map);
    }

    @Transactional
    public PersonReadDto create(PersonCreateEditDto personDto){
        return Optional.of(personDto)
                .map(personCreateEditMapper::map)
                .map(personRepository::save)
                .map(personReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<PersonReadDto> update(Long id, PersonCreateEditDto personDto){
        return personRepository.findById(id)
                .map(entity -> personCreateEditMapper.map(personDto, entity))
                .map(personRepository::saveAndFlush)
                .map(personReadMapper::map);
    }

    @Transactional
    public boolean delete(Long id){
        return personRepository.findById(id)
                .map(entity -> {
                    personRepository.delete(entity);
                    personRepository.flush();
                    return true;
                })
                .orElse(false);
    }

}
