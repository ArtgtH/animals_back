package com.animals_back.service;

import com.animals_back.entity.Animal;

import java.util.List;

public interface AnimalService {

    public List<Animal> getAllAnimal();

    public void saveAnimal(Animal animal);

    public Animal getAnimal(int id);

    public void deleteAnimal(int id);
}
