package com.animals_back.service;

import com.animals_back.dao.AnimalDAO;
import com.animals_back.entity.Animal;
import com.animals_back.exceptions.AnimalAlreadyExistException;
import com.animals_back.exceptions.AnimalNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnimalService {
    private final AnimalDAO animalDAO;
    private final ObjectMapper objectMapper;

    public List<Animal> getAllAnimal() {
        return animalDAO.findAll();
    }

    @Transactional
    public void saveAnimal(MultipartFile file, String json) throws IOException, AnimalAlreadyExistException {
        Animal animal = objectMapper.readValue(json, Animal.class);
        byte[] image;
        if (!file.isEmpty()) {
            image = file.getBytes();
            animal.setPhoto(image);
        }
        animalDAO.save(animal);
    }

    public Optional<Animal> findAnimalById(int id) throws AnimalNotFoundException {
        return Optional.ofNullable(animalDAO.findById(id).orElseThrow(() -> new AnimalNotFoundException(id)));
    }

    public void deleteAnimalById(int id) throws AnimalNotFoundException {
        Optional<Animal> animal = this.findAnimalById(id);
        if (animal.isPresent()) {
            animalDAO.deleteById(id);
        } else {
            throw new AnimalNotFoundException(id);
        }
    }
}
