package com.example.succulentum.api.controllers;

import com.example.succulentum.api.dto.AckDto;
import com.example.succulentum.api.dto.PlantDto;
import com.example.succulentum.api.exceprions.BadRequestException;
import com.example.succulentum.api.factories.PlantDtoFactory;
import com.example.succulentum.store.entities.PlantEntity;
import com.example.succulentum.store.repositories.PlantRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Transactional
@RestController
public class PlantController {
    private final PlantRepository plantRepository;
    private final PlantDtoFactory plantDtoFactory;

    public static final String CREATE_OR_UPDATE_PLANT = "/api/plant";
    public static final String DELETE_PLANT = "/api/plant/{id}";

    @PostMapping(CREATE_OR_UPDATE_PLANT)
    public PlantDto createPlant(
            @RequestParam String russianName,
            @RequestParam String latinName,
            @RequestParam String description
    )
    {
        plantRepository
                .findByRussianName(russianName)
                .ifPresent(_ -> {
                    throw new BadRequestException("Plant already exists");
                });
        PlantEntity plantEntity = plantRepository.saveAndFlush(
                PlantEntity.builder()
                        .russianName(russianName)
                        .latinName(latinName)
                        .description(description)
                        .build()
        );
        return plantDtoFactory.makePlantDto(plantEntity);
    }

    @DeleteMapping(DELETE_PLANT)
    public AckDto deletePlant(@PathVariable Long id) {
        plantRepository.deleteById(id);
        return AckDto.makeDefault(true);
    }
}
