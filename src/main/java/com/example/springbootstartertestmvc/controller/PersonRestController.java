package com.example.springbootstartertestmvc.controller;

import com.example.springbootstartertestmvc.dto.PersonCreateEditDto;
import com.example.springbootstartertestmvc.dto.PersonReadDto;
import com.example.springbootstartertestmvc.service.PersonService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


/**
 * @author Zhurenkov Pavel 24.03.2023
 */

@RestController
@RequestMapping("/api/persons")
@RequiredArgsConstructor
public class PersonRestController {

    private final PersonService personService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PersonReadDto> findAll(){
        return personService.findAll();
    }

    @GetMapping("/{id}")
    public PersonReadDto findById(@PathVariable("id") Long id){
        return personService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{name}")
    public ResponseEntity<PersonReadDto> findByName(@PathVariable("name") String name){
        Optional<PersonReadDto> person = personService.findByName(name);
        if (!person.isPresent())
            throw new EntityNotFoundException("name -" + name);
        return ResponseEntity.ok().body(person.get());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PersonReadDto create(@RequestBody @Valid PersonCreateEditDto person){
        return personService.create(person);
    }

    @PutMapping ("/{id}")
    public PersonReadDto update(@PathVariable("id") Long id,
                                @RequestBody PersonCreateEditDto person){
        return personService.update(id, person)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        return personService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

}
