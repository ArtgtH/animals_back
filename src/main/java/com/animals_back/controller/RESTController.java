package com.animals_back.controller;

import com.animals_back.entity.Animal;
import com.animals_back.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class RESTController {

    @Autowired
    private AnimalService animalService;

    @GetMapping("/animals")
    public List<Animal> showAllAnimals() {
        return animalService.getAllAnimal();
    }

}
