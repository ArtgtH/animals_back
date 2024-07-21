package com.animals_back.services;

import com.animals_back.DTO.AnimalDTO;
import com.animals_back.DTO.CreateNewAnimalDTO;
import com.animals_back.entities.Shelter;
import com.animals_back.repositories.AnimalRepository;
import com.animals_back.entities.Animal;
import com.animals_back.exceptions.AnimalAlreadyExistException;
import com.animals_back.exceptions.AnimalNotFoundException;
import com.animals_back.repositories.ShelterRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Сервис для обработки различных действий со зверьми
 */
@Service
@RequiredArgsConstructor
public class AnimalService {
    private final AnimalRepository animalRepository;
    private final ObjectMapper objectMapper;
    private final ShelterRepository shelterRepository;

    /**
     * Метод для поиска зверей в базе данных
     *
     * @return - список всех зверей
     */
    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }

    /**
     * Метод изменяет данные уже существующего зверька в базе
     *
     * @param id   - id зверька
     * @param file - файл-изображение, из которого мы возьмем последовательность байт
     * @param json - json, который содержит в себе поля класса Animal
     * @throws IOException             - метод может выбросить IOException, если возникнут проблемы с чтением json
     * @throws AnimalNotFoundException - метод может выбросить AnimalNotFoundException, если зверька с таким id нет в базе
     */
    @Transactional
    public void updateAnimal(int id, MultipartFile file, String json) throws IOException, AnimalNotFoundException {
        Animal newAnimal = objectMapper.readValue(json, Animal.class);
        Animal animal = this.findAnimalById(id).orElseThrow(() -> new AnimalNotFoundException(id));
        animal.setName(newAnimal.getName());
        animal.setAge(newAnimal.getAge());
        animal.setSex(newAnimal.getSex());
        animal.setWeight(newAnimal.getWeight());
        animal.setHeight(newAnimal.getHeight());
        animal.setDescription(newAnimal.getDescription());
        animal.setShelter(newAnimal.getShelter());

        if (file != null) {
            byte[] image = file.getBytes();
            animal.setPhoto(image);
        }
        animalRepository.save(animal);
    }

    /**
     * Метод сохраняет нового зверька в базе данных
     *
     * @param file - файл-изображение, из которого мы возьмем последовательность байт
     * @param json - json, который содержит в себе поля класса Animal
     * @throws IOException                 - метод может выбросить IOException, если возникнут проблемы с чтением json
     * @throws AnimalAlreadyExistException - метод может выбросить AnimalAlreadyExistException, если такой зверек уже существует
     */
    @Transactional
    public void saveAnimal(MultipartFile file, String json) throws IOException, AnimalAlreadyExistException {
        CreateNewAnimalDTO animalDTO = objectMapper.readValue(json, CreateNewAnimalDTO.class);
        Animal animal = new Animal();
        Optional<Shelter> shelter = shelterRepository.findById(animalDTO.getShelterId());
        if (file != null) {
            byte[] image = file.getBytes();
            animal.setPhoto(image);
        }
        animal.setName(animalDTO.getName());
        animal.setAge(animalDTO.getAge());
        animal.setDescription(animalDTO.getDescription());
        animal.setSex(animalDTO.getDescription());
        animal.setWeight(animalDTO.getWeight());
        animal.setHeight(animalDTO.getHeight());
        animal.setShelter(shelter.get());
        animalRepository.save(animal);
    }

    /**
     * Метод находит определенного зверька по его id
     *
     * @param id - id зверька
     * @return - определенного зверька
     * @throws AnimalNotFoundException - метод может выбросить AnimalNotFoundException, если зверька с таким id нет в базе
     */
    public Optional<Animal> findAnimalById(int id) throws AnimalNotFoundException {
        return Optional.ofNullable(animalRepository.findById(id).orElseThrow(() -> new AnimalNotFoundException(id)));
    }

    /**
     * Метод удаляет определенного зверька по его id
     *
     * @param id - id зверька
     * @throws AnimalNotFoundException - метод может выбросить AnimalNotFoundException, если зверька с таким id нет в базе
     */
    public void deleteAnimalById(int id) throws AnimalNotFoundException {
        Optional<Animal> animal = this.findAnimalById(id);
        if (animal.isPresent()) {
            animalRepository.deleteById(id);
        } else {
            throw new AnimalNotFoundException(id);
        }
    }
}
