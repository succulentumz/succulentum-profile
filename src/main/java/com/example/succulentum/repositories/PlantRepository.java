package com.example.succulentum.repositories;

import com.example.succulentum.models.Plant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlantRepository extends JpaRepository<Plant, Long> {
    List<Plant> findByEnglishName(String plantName);
}
