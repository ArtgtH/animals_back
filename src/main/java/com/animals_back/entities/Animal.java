package com.animals_back.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "animals")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "photo_path")
    private String photoPath;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private String age;

    @Column(name = "weight")
    private String weight;

    @Column(name = "height")
    private String height;

    @Column(name = "sex")
    private String sex;

    @Column(name = "description", length = 100000)
    private String description;

    @ManyToOne
    @JoinColumn(name = "shelter_id", nullable = false)
    @JsonBackReference
    private Shelter shelter;
}

