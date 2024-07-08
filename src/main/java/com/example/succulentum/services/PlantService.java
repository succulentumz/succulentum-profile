package com.example.succulentum.services;

import com.example.succulentum.models.Plant;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PlantService {
    @Getter
    private List<Plant> plantList = new ArrayList<>();
    private Long ID = 0L;

    {
        plantList.add(new Plant(++ID, "suc1", "latin1", "suc1 lalalallalaalla"));
        plantList.add(new Plant(++ID, "suc2",  "latin2", "suc2 lalalalalaalala"));
    }

    public void addPlant(Plant plant) {
        plant.setId(++ID);
        plantList.add(plant);
    }

    public void deletePlant(Long id) {
        plantList.removeIf(plant -> Objects.equals(plant.getId(), id));
    }

    public Plant getPlantById(Long id) {
        return plantList.stream().filter(plant -> Objects.equals(plant.getId(), id)).findFirst().orElse(null);
    }
}
