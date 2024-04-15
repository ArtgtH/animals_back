package com.animals_back.services;

import com.animals_back.repositories.AnimalRepository;
import com.animals_back.entities.Animal;
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
    private final AnimalRepository animalRepository;
    private final ObjectMapper objectMapper;

    public List<Animal> getAllAnimal() {
        return animalRepository.findAll();
    }

    @Transactional
    public void updateAnimal(int id, MultipartFile file, String json) throws IOException, AnimalNotFoundException {
        Animal newAnimal = objectMapper.readValue(json, Animal.class);
        Optional<Animal> animal = this.findAnimalById(id);
        if (animal.isPresent()) {
            animal.get().setName(newAnimal.getName());
            animal.get().setAge(newAnimal.getAge());
            animal.get().setSex(newAnimal.getSex());
            animal.get().setWeight(newAnimal.getWeight());
            animal.get().setHeight(newAnimal.getHeight());
            animal.get().setDescription(newAnimal.getDescription());
            animal.get().setShelter(newAnimal.getShelter());

            if (file != null) {
                byte[] image = file.getBytes();
                animal.get().setPhoto(image);
            }
            animalRepository.save(animal.get());
        } else {
            throw new AnimalNotFoundException(newAnimal.getId());
        }
    }

    @Transactional
    public void saveAnimal(MultipartFile file, String json) throws IOException, AnimalAlreadyExistException {
        Animal animal = objectMapper.readValue(json, Animal.class);
        if (file != null) {
            byte[] image = file.getBytes();
            animal.setPhoto(image);
        }
        animalRepository.save(animal);
    }

    public Optional<Animal> findAnimalById(int id) throws AnimalNotFoundException {
        return Optional.ofNullable(animalRepository.findById(id).orElseThrow(() -> new AnimalNotFoundException(id)));
    }

    public void deleteAnimalById(int id) throws AnimalNotFoundException {
        Optional<Animal> animal = this.findAnimalById(id);
        if (animal.isPresent()) {
            animalRepository.deleteById(id);
        } else {
            throw new AnimalNotFoundException(id);
        }
    }
}
