package com.animals_back.service;

import com.animals_back.dao.AnimalDAO;
import com.animals_back.entity.Animal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnimalService {
    private final AnimalDAO animalDAO;

    public List<Animal> getAllAnimal() {
        return animalDAO.findAll();
    }

    public void saveAnimal(Animal animal) {
        animalDAO.save(animal);
    }

    public Optional<Animal> findAnimalById(int id) {
        return animalDAO.findById(id);
    }

    public void deleteAnimalById(int id) {
        animalDAO.deleteById(id);
    }
}
