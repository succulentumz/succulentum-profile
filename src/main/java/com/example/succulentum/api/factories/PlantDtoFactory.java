package com.example.succulentum.api.factories;

import com.example.succulentum.api.dto.PlantDto;
import com.example.succulentum.store.entities.PlantEntity;
import org.springframework.stereotype.Component;

@Component
public class PlantDtoFactory {
    public PlantDto makePlantDto(PlantEntity plantEntity) {
        return PlantDto.builder()
                .id(plantEntity.getId())
                .russianName(plantEntity.getRussianName())
                .latinName(plantEntity.getLatinName())
                .description(plantEntity.getDescription())
                .build();
    }
}
