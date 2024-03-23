package com.animals_back.controller;

import com.animals_back.entity.Animal;
import com.animals_back.entity.Shelter;
import com.animals_back.exceptions.AnimalNotFoundException;
import com.animals_back.service.AnimalService;
import com.animals_back.service.ShelterService;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RESTController {
    private final AnimalService animalService;
    private final ShelterService shelterService;

    @GetMapping("/animals")
    public ResponseEntity<?> getAllAnimals() {
        List<Animal> animals = animalService.getAllAnimal();
        return ResponseEntity.status(HttpStatus.OK).body(animals);
    }

    @GetMapping("/shelters")
    public ResponseEntity<?> getAllShelters() {
        List<Shelter> shelters = shelterService.getAllShelters();
        return ResponseEntity.status(HttpStatus.OK).body(shelters);
    }

    @GetMapping("/animal/{id}")
    public ResponseEntity<?> getAnimalById(@PathVariable("id") Integer id) {
        try {
            Optional<Animal> animal = animalService.findAnimalById(id);
            return ResponseEntity.status(HttpStatus.OK).body(animal);
        } catch (AnimalNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @DeleteMapping("/deleteAnimal/{id}")
    public ResponseEntity<?> deleteAnimalById(@PathVariable("id") Integer id) {
        try {
            animalService.deleteAnimalById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(String.format("Зверь под id %d был успешно удален", id));
        } catch (AnimalNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }
}
