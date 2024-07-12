package com.example.succulentum.store.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "plants")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;
    private String russianName;
    private String latinName;
    @Column(columnDefinition = "text")
    private String description;
}
