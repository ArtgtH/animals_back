package com.animals_back.service;

import com.animals_back.dao.AnimalDAO;
import com.animals_back.entity.Animal;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalServiceImplementation implements AnimalService{

    @Autowired
    private AnimalDAO animalDAO;

    @Override
    public List<Animal> getAllAnimal() {
        return animalDAO.getAllAnimals();
    }

    @Override
    @Transactional
    public void saveAnimal(Animal animal) {
        animalDAO.saveAnimal(animal);
    }

    @Override
    public Animal getAnimal(int id) {
        return animalDAO.getAllAboutAnimal(id);
    }

    @Override
    public void deleteAnimal(int id) {
        animalDAO.deleteAnimal(id);
    }
}
