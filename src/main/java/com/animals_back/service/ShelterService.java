package com.animals_back.service;

import com.animals_back.dao.ShelterDAO;
import com.animals_back.entity.Animal;
import com.animals_back.entity.Shelter;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.cache.spi.SecondLevelCacheLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShelterService {

    private final ShelterDAO shelterDAO;

    public List<Shelter> getAllShelters() {
        return shelterDAO.findAll();
    }
}
