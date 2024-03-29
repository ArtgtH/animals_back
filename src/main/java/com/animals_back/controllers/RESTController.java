package com.animals_back.controllers;

import com.animals_back.entities.Animal;
import com.animals_back.exceptions.AnimalAlreadyExistException;
import com.animals_back.exceptions.AnimalNotFoundException;
import com.animals_back.services.AnimalService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RESTController {
    private final AnimalService animalService;

    @GetMapping("/animals")
    public ResponseEntity<?> getAllAnimals() {
        List<Animal> animals = animalService.getAllAnimal();
        return ResponseEntity.status(HttpStatus.OK).body(animals);
    }

    @PutMapping("save-animal")
    public ResponseEntity<String> saveAnimal(@Nullable @RequestParam("image") MultipartFile multipartFile,
                                             @RequestParam("json") String json) throws IOException {
        try {
            animalService.saveAnimal(multipartFile, json);
            return ResponseEntity.status(HttpStatus.CREATED).body("Зверёк был успешно сохранен.");
        } catch (AnimalAlreadyExistException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }

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

    @DeleteMapping("/delete-animal/{id}")
    public ResponseEntity<String> deleteAnimalById(@PathVariable("id") Integer id) {
        try {
            animalService.deleteAnimalById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(String.format("Зверь под id %d был успешно удален", id));
        } catch (AnimalNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }
}