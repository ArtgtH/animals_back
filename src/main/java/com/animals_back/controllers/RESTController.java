package com.animals_back.controllers;

import com.animals_back.entities.Animal;
import com.animals_back.exceptions.AnimalAlreadyExistException;
import com.animals_back.exceptions.AnimalNotFoundException;
import com.animals_back.services.AnimalService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@EnableAspectJAutoProxy
public class RESTController {
    private final AnimalService animalService;

    @GetMapping("/animals")
    public ResponseEntity<?> getAllAnimals() {
        List<Animal> animals = animalService.getAllAnimal();
        return ResponseEntity.status(HttpStatus.OK).body(animals);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/save-animal")
    public ResponseEntity<?> saveAnimal(@Nullable @RequestParam("image") MultipartFile multipartFile,
                                             @RequestParam("json") String json) throws IOException {
        try {
            animalService.saveAnimal(multipartFile, json);
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Зверек был успешно добавлен!"));
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
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body((Map.of("message", exception.getMessage())));
        }
    }

    @PostMapping("/update-animal/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updateAnimal(@PathVariable("id") Integer id, @RequestParam("image") MultipartFile multipartFile, @RequestParam("json") String json) throws IOException {
        try {
            animalService.updateAnimal(id, multipartFile, json);
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Зверек был успешно обновлён!"));
        } catch (AnimalNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((Map.of("message", e.getMessage())));
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete-animal/{id}")
    public ResponseEntity<?> deleteAnimalById(@PathVariable("id") Integer id) {
        try {
            animalService.deleteAnimalById(id);
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", String.format("Зверь под id %d был успешно удален", id)));
        } catch (AnimalNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body((Map.of("message", exception.getMessage())));
        }
    }

    @GetMapping("/info")
    public String userData(Principal principal) {
        return principal.getName();
    }
}
