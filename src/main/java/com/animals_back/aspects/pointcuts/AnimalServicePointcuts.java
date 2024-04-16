package com.animals_back.aspects.pointcuts;

import org.aspectj.lang.annotation.Pointcut;

public class AnimalServicePointcuts {
    @Pointcut("execution(public java.util.Optional<com.animals_back.entities.Animal> findAnimalById(int)) throws")
    public void findAnimalByIdPointcut() {}

    @Pointcut("execution(public java.util.List<com.animals_back.entities.Animal> getAllAnimals())")
    public void getAllAnimalsPointcut() {}
}
