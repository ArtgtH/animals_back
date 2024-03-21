package com.animals_back.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "animals")

public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "photo")
    @Lob
    private byte[] photo;

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "weight")
    private float weight;

    @Column(name = "height")
    private float height;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shelter_id")
    private Shelter shelter;

    public Animal() {
    }

    public Animal(String name, int age, float weight, float height, Shelter shelter) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.shelter = shelter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public Shelter getShelter() {
        return shelter;
    }

    public void setShelter(Shelter shelter) {
        this.shelter = shelter;
    }
}

