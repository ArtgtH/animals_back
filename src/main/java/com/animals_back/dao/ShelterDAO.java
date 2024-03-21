package com.animals_back.dao;

import com.animals_back.entity.Shelter;

import java.util.List;

public interface ShelterDAO {
    public List<Shelter> getAllShelters();

    public void saveShelter(Shelter shelter);

    public void deleteShelter(int id);

    public void getAllAboutShelter(int id);
}
