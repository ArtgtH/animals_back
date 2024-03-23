package com.animals_back.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
@Data
public class AnimalNotFoundException extends Exception {
    private final Integer animalId;

    @Override
    public String getMessage() {
        return String.format("Пользователь с id %d не найден", animalId);
    }
}
