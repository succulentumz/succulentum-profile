package com.example.succulentum.store.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "plants")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "englishName")
    private String englishName;

    @Column(name = "latinName")
    private String latinName;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    //    private String imageUrl;

}
