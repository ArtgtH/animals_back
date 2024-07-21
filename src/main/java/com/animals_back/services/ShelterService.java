package com.animals_back.services;

import com.animals_back.DTO.AddNewShelterDTO;
import com.animals_back.repositories.ShelterRepository;
import com.animals_back.entities.Shelter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис для обработки различных действий с приютами
 */
@Service
@RequiredArgsConstructor
public class ShelterService {
    public final ShelterRepository shelterRepository;

    public Shelter saveShelter(AddNewShelterDTO addNewShelterDTO) {
        Shelter shelter = new Shelter();
        shelter.setName(addNewShelterDTO.getName());
        shelter.setAddress(addNewShelterDTO.getAddress());
        shelter.setTelephone(addNewShelterDTO.getPhone());
        return shelterRepository.save(shelter);
    }
}
