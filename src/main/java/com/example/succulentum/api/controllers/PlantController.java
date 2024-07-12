package com.example.succulentum.api.controllers;

import com.example.succulentum.api.dto.AckDto;
import com.example.succulentum.api.dto.PlantDto;
import com.example.succulentum.api.exceptions.BadRequestException;
import com.example.succulentum.api.exceptions.NotFoundException;
import com.example.succulentum.api.factories.PlantDtoFactory;
import com.example.succulentum.store.entities.PlantEntity;
import com.example.succulentum.store.repositories.PlantRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Transactional
@RestController
public class PlantController {
    private final PlantRepository plantRepository;
    private final PlantDtoFactory plantDtoFactory;

    public static final String FETCH_PLANT = "/api/plant";
    public static final String CREATE_PLANT = "/api/plant";
    public static final String EDIT_PLANT = "/api/plant/{plantId}";
    public static final String DELETE_PLANT = "/api/plant/{id}";

    @GetMapping(FETCH_PLANT)
    public List<PlantDto> fetchPlants(
            @RequestParam(value = "prefix_name", required = false) Optional<String> optionalPrefixName
    ) {
        optionalPrefixName = optionalPrefixName
                .filter(prefixName -> !prefixName.trim().isEmpty());

        Stream<PlantEntity> plantEntityStream = optionalPrefixName.isPresent()
                ? plantRepository.streamAllByRussianNameStartsWith(optionalPrefixName.get())
                : plantRepository.streamAll();

        return plantEntityStream
                .map(plantDtoFactory::makePlantDto)
                .collect(Collectors.toList());
    }

    @PostMapping(CREATE_PLANT)
    public PlantDto createPlant(
            @RequestParam String russianName,
            @RequestParam String latinName,
            @RequestParam String description
    ) {
        plantRepository
                .findByRussianName(russianName)
                .ifPresent(_ -> {
                    throw new BadRequestException(String.format("Plant \"%s\" already exists", russianName));
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

    @PatchMapping(EDIT_PLANT)
    public PlantDto editPlant(
            @PathVariable("plantId") Long plantId,
            @RequestParam String russianName
    ) {
        if (russianName.trim().isEmpty()) {
            throw new BadRequestException("Russian name cannot be empty");
        }

        PlantEntity plantEntity = getPlantEntityOrThrowException(plantId);
        plantRepository
                .findByRussianName(russianName)
                .filter(anotherPlantEntity -> !Objects.equals(anotherPlantEntity.getId(), plantEntity.getId()))
                .ifPresent(anotherPlantEntity -> {
                    throw new BadRequestException(String.format("Plant \"%s\" already exists", russianName));
                });
        plantEntity.setRussianName(russianName);
        plantRepository.saveAndFlush(plantEntity);

        return plantDtoFactory.makePlantDto(plantEntity);
    }

    @DeleteMapping(DELETE_PLANT)
    public AckDto deletePlant(@PathVariable Long id) {
        getPlantEntityOrThrowException(id);
        plantRepository.deleteById(id);
        return AckDto.makeDefault(true);
    }

    private PlantEntity getPlantEntityOrThrowException(Long id) {
        return plantRepository
                .findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                String.format(
                                        "Plant with \"%s\" doesn't exist.",
                                        id
                                )
                        )
                );
    }
}
