package com.animals_back.services;

import com.animals_back.DTO.AddNewShelterDTO;
import com.animals_back.DTO.AnimalDTO;
import com.animals_back.DTO.ShelterDTO;
import com.animals_back.entities.Animal;
import com.animals_back.entities.Shelter;
import com.animals_back.exceptions.AnimalAlreadyExistException;
import com.animals_back.exceptions.AnimalNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Сервис для обработки REST-запросов, связанных с животными и приютами.
 */
@Service
@RequiredArgsConstructor
public class RestService {
    private final AnimalService animalService;
    private final ShelterService shelterService;

    /**
     * Метод для получения списка всех животных.
     *
     * @return ResponseEntity со списком животных и статусом HTTP OK.
     */
    public ResponseEntity<List<AnimalDTO>> getAllAnimals() {
        List<Animal> animals = animalService.getAllAnimals();
        List<AnimalDTO> animalDTOs = animals.stream()
                .map(animal ->
                        AnimalDTO.builder()
                                .id(animal.getId())
                                .name(animal.getName())
                                .age(animal.getAge())
                                .weight(animal.getWeight())
                                .height(animal.getHeight())
                                .sex(animal.getSex())
                                .description(animal.getDescription())
                                .photoPath(animal.getPhotoPath())
                                .shelter(ShelterDTO.builder()
                                        .id(animal.getShelter().getId())
                                        .name(animal.getShelter().getName())
                                        .phone(animal.getShelter().getTelephone())
                                        .address(animal.getShelter().getAddress())
                                        .build())
                                .build()
                )
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(animalDTOs);
    }

    /**
     * Метод для сохранения нового животного.
     *
     * @param multipartFile файл с изображением животного.
     * @param json          JSON строка с данными животного.
     * @return ResponseEntity с сообщением об успешном добавлении животного или ошибкой.
     * @throws IOException если произошла ошибка при чтении файла или парсинге JSON.
     */
    public ResponseEntity<?> saveAnimal(MultipartFile multipartFile, String json) throws IOException {
        try {
            animalService.saveAnimal(multipartFile, json);
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Зверек был успешно добавлен!"));
        } catch (AnimalAlreadyExistException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    /**
     * Метод для получения животного по его ID.
     *
     * @param id идентификатор животного.
     * @return ResponseEntity с данными животного или ошибкой.
     */
    public ResponseEntity<?> getAnimalById(Integer id) {
        try {
            Optional<Animal> animal = animalService.findAnimalById(id);
            AnimalDTO animalDTO = AnimalDTO.builder()
                    .id(animal.get().getId())
                    .name(animal.get().getName())
                    .age(animal.get().getAge())
                    .weight(animal.get().getWeight())
                    .height(animal.get().getHeight())
                    .sex(animal.get().getSex())
                    .description(animal.get().getDescription())
                    .photoPath(animal.get().getPhotoPath())
                    .shelter(ShelterDTO.builder()
                            .id(animal.get().getShelter().getId())
                            .name(animal.get().getShelter().getName())
                            .phone(animal.get().getShelter().getTelephone())
                            .address(animal.get().getShelter().getAddress())
                            .build())
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(animalDTO);
        } catch (AnimalNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", exception.getMessage()));
        }
    }

    /**
     * Метод для обновления данных животного.
     *
     * @param id            идентификатор животного.
     * @param multipartFile файл с изображением животного.
     * @param json          JSON строка с данными животного.
     * @return ResponseEntity с сообщением об успешном обновлении животного или ошибкой.
     * @throws IOException если произошла ошибка при чтении файла или парсинге JSON.
     */
    public ResponseEntity<?> updateAnimal(Integer id, MultipartFile multipartFile, String json) throws IOException {
        try {
            animalService.updateAnimal(id, multipartFile, json);
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Зверек был успешно обновлён!"));
        } catch (AnimalNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
        }
    }

    /**
     * Метод для удаления животного по его ID.
     *
     * @param id идентификатор животного.
     * @return ResponseEntity с сообщением об успешном удалении животного или ошибкой.
     */
    public ResponseEntity<?> deleteAnimalById(Integer id) {
        try {
            animalService.deleteAnimalById(id);
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", String.format("Зверь под id %d был успешно удален", id)));
        } catch (AnimalNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", exception.getMessage()));
        }
    }

    /**
     * Метод для получения данных пользователя.
     *
     * @param principal объект, представляющий текущего аутентифицированного пользователя.
     * @return карта с именем пользователя.
     */
    public Map<String, String> userData(Principal principal) {
        Map<String, String> response = new HashMap<>();
        response.put("username", principal.getName());
        return response;
    }

    /**
     * Метод для добавления нового приюта.
     *
     * @param addNewShelterDTO объект с данными нового приюта.
     * @return ResponseEntity с сообщением об успешном добавлении приюта.
     */
    public ResponseEntity<?> addNewShelter(AddNewShelterDTO addNewShelterDTO) {
        shelterService.saveShelter(addNewShelterDTO);
        return ResponseEntity.ok().body(Map.of("message", "Новый приют успешно добавлен!"));
    }

    public ResponseEntity<?> getAllShelters() {
        List<Shelter> shelters = shelterService.getAllShelters();
        return ResponseEntity.ok().body(shelters);
    }
}