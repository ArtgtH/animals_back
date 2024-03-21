package com.animals_back.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "shelters")
public class Shelter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "address")
    private String address;

    @Column(name = "telephone")
    private int telephone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shelter", fetch = FetchType.EAGER)
    private List<Animal> animals;

    public Shelter() {
    }

    public Shelter(String address, int telephone, List<Animal> animals) {
        this.address = address;
        this.telephone = telephone;
        this.animals = animals;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }
}
