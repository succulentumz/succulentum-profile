package com.example.succulentum.store.repositories;

import com.example.succulentum.store.entities.PlantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlantRepository extends JpaRepository<PlantEntity, Long> {
    Optional<PlantEntity> findByRussianName(String russianName);
}
