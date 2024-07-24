package com.animals_back.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnimalDTO {
    private Integer id;
    private String name;
    private String age;
    private String weight;
    private String height;
    private String sex;
    private String photoPath;
    private String description;
    private ShelterDTO shelter;
}