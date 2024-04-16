package com.animals_back.services;

import com.animals_back.entities.Animal;
import com.animals_back.exceptions.AnimalAlreadyExistException;
import com.animals_back.exceptions.AnimalNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RestService {
    private final AnimalService animalService;

    public ResponseEntity<?> getAllAnimals() {
        List<Animal> animals = animalService.getAllAnimals();
        return ResponseEntity.status(HttpStatus.OK).body(animals);
    }

    public ResponseEntity<?> saveAnimal(MultipartFile multipartFile,
                                        String json) throws IOException {
        try {
            animalService.saveAnimal(multipartFile, json);
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Зверек был успешно добавлен!"));
        } catch (AnimalAlreadyExistException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }

    }

    public ResponseEntity<?> getAnimalById(Integer id) {
        try {
            Optional<Animal> animal = animalService.findAnimalById(id);
            return ResponseEntity.status(HttpStatus.OK).body(animal);
        } catch (AnimalNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body((Map.of("message", exception.getMessage())));
        }
    }

    public ResponseEntity<?> updateAnimal(Integer id, MultipartFile multipartFile, String json) throws IOException {
        try {
            animalService.updateAnimal(id, multipartFile, json);
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Зверек был успешно обновлён!"));
        } catch (AnimalNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((Map.of("message", e.getMessage())));
        }
    }

    public ResponseEntity<?> deleteAnimalById(Integer id) {
        try {
            animalService.deleteAnimalById(id);
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", String.format("Зверь под id %d был успешно удален", id)));
        } catch (AnimalNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body((Map.of("message", exception.getMessage())));
        }
    }

    public String userData(Principal principal) {
        return principal.getName();
    }
}
