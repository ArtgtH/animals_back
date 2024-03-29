package com.animals_back.repositories;

import com.animals_back.entities.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ShelterRepository extends JpaRepository<Shelter, Integer> {

}
