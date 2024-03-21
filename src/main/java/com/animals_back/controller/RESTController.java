package com.animals_back.controller;

import com.animals_back.entity.Animal;
import com.animals_back.entity.Shelter;
import com.animals_back.service.AnimalService;
import com.animals_back.service.ShelterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RESTController {
    private final AnimalService animalService;
    private final ShelterService shelterService;

    @GetMapping("/animals")
    public ResponseEntity<?> showAllAnimals() {
        List<Animal> animals = animalService.getAllAnimal();
        return ResponseEntity.status(HttpStatus.OK).body(animals);
    }
    @GetMapping("/shelters")
    public ResponseEntity<?> showAllShelters() {
        List<Shelter> shelters = shelterService.getAllShelters();
        return ResponseEntity.status(HttpStatus.OK).body(shelters);
    }
}
