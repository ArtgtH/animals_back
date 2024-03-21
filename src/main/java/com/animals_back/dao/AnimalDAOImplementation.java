package com.animals_back.dao;

import com.animals_back.entity.Animal;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AnimalDAOImplementation implements AnimalDAO{

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Animal> getAllAnimals() {
        Query query = entityManager.createQuery("from Animal");
        return query.getResultList();
    }

    @Override
    public void saveAnimal(Animal animal) {
        entityManager.merge(animal);
    }

    @Override
    @Transactional
    public void deleteAnimal(int id) {
        Query query = entityManager.createQuery("delete from Animal where id =:animalId");
        query.setParameter("animalId", id);
        query.executeUpdate();
    }

    @Override
    public Animal getAllAboutAnimal(int id) {
        return entityManager.find(Animal.class, id);
    }
}
