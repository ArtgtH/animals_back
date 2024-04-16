package com.animals_back.aspects.pointcuts;

import org.aspectj.lang.annotation.Pointcut;

public class RestServicePointcuts {
    @Pointcut("execution(public org.springframework.http.ResponseEntity com.animals_back.services.RestService.getAnimalById(Integer))")
    public void getAnimalByIdPointcut() {}

    @Pointcut("execution(public org.springframework.http.ResponseEntity com.animals_back.services.RestService.getAllAnimals())")
    public void getAllAnimalsPointcut() {}
}
