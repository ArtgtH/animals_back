package com.animals_back.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
@Data
public class AnimalAlreadyExistException extends Exception {
    private final Integer animalId;

    @Override
    public String getMessage() {
        return String.format("Зверёк с id %d уже существует", animalId);
    }
}
