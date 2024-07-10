package com.example.succulentum.services;

import com.example.succulentum.models.Plant;
import com.example.succulentum.repositories.PlantRepository;
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
    private List<Plant> plantList = new ArrayList<>();

    public List<Plant> listPlants(
            String englishName
    ) {
        if (englishName == null) {
            return plantRepository.findAll();
        }
        return plantRepository.findByEnglishName(englishName);
    }

    public void savePlant(
            Plant plant
    ) {
        log.info("saveUser plant: {}", plant);
        plantRepository.save(plant);
    }

    public void deletePlant(Long id) {
        plantRepository.deleteById(id);
    }

    public Plant getPlantById(Long id) {
        return plantRepository.findById(id).orElse(null);
    }

    public void deleteAllPlants() {
        plantRepository.deleteAll();
    }
}
