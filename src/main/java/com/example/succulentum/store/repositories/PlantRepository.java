package com.example.succulentum.store.repositories;

import com.example.succulentum.store.entities.PlantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlantRepository extends JpaRepository<PlantEntity, Long> {
    List<PlantEntity> findByEnglishName(String plantName);
}
