package com.example.succulentum.store.repositories;

import com.example.succulentum.store.entities.PlantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface PlantRepository extends JpaRepository<PlantEntity, Long> {
    Optional<PlantEntity> findByRussianName(String russianName);

    Stream<PlantEntity> streamAll();
    Stream<PlantEntity> streamAllByRussianNameStartsWith(String prefixName);
}
