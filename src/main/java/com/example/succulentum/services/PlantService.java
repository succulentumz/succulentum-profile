package com.example.succulentum.services;

import com.example.succulentum.store.entities.PlantEntity;
import com.example.succulentum.store.repositories.PlantRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlantService {

    private final PlantRepository plantRepository;

    @Getter
    private List<PlantEntity> plantEntityList = new ArrayList<>();

    public List<PlantEntity> listPlants(
            String englishName
    ) {
        if (englishName == null) {
            return plantRepository.findAll();
        }
        return plantRepository.findByEnglishName(englishName);
    }

    public void savePlant(
            PlantEntity plantEntity
    ) {
        log.info("saveUser plantEntity: {}", plantEntity);
        plantRepository.save(plantEntity);
    }

    public void deletePlant(Long id) {
        plantRepository.deleteById(id);
    }

    public PlantEntity getPlantById(Long id) {
        return plantRepository.findById(id).orElse(null);
    }

    public void deleteAllPlants() {
        plantRepository.deleteAll();
    }
}
