package com.animals_back.services;

import com.animals_back.DTO.AddNewAnimalTypeDto;
import com.animals_back.entities.AnimalType;
import com.animals_back.entities.Shelter;
import com.animals_back.repositories.AnimalTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalTypeService {

    public final AnimalTypeRepository animalTypeRepository;

    /**
     * Метод для сохранения нового приюта.
     *
     * @param addNewAnimalTypeDto объект DTO с данными нового приюта.
     * @return сохраненный объект приюта.
     */
    public AnimalType saveAnimalType(AddNewAnimalTypeDto addNewAnimalTypeDto) {
        AnimalType animalType = new AnimalType();
        animalType.setName(addNewAnimalTypeDto.getName());
        return animalTypeRepository.save(animalType);
    }

    public List<AnimalType> getAllAnimalType() {
        return animalTypeRepository.findAll();
    }
}
