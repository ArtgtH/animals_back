package com.animals_back.dao;

import com.animals_back.entity.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ShelterDAO extends JpaRepository<Shelter, Integer> {

}
