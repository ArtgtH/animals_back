package com.animals_back.dao;

import com.animals_back.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AnimalDAO extends JpaRepository<Animal, Integer> {
    Optional<Animal> findAnimalByName(String name);
}
