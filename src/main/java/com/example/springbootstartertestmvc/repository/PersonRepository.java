package com.example.springbootstartertestmvc.repository;

import com.example.springbootstartertestmvc.entity.Person;
import com.example.springbootstartertestmvc.mapper.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Zhurenkov Pavel 24.03.2023
 */

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByName(String name);
}
