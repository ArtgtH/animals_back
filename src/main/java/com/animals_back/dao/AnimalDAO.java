package com.animals_back.dao;

import com.animals_back.entity.Animal;

import java.util.List;

public interface AnimalDAO {

    public List<Animal> getAllAnimals();

    public void saveAnimal(Animal animal);

    public void deleteAnimal(int id);

    public Animal getAllAboutAnimal(int id);
}
