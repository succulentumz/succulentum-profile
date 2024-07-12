package com.example.succulentum.api.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class PlantDto {
    private Long id;
    private String russianName;
    private String latinName;
    private String description;
}
