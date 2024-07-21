package com.animals_back.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateNewAnimalDTO {
    private String name;
    private String age;
    private String weight;
    private String height;
    private String sex;
    private byte[] photo;
    private String description;
    private Integer shelterId;
}
