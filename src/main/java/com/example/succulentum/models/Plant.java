package com.example.succulentum.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Plant {
    private Long id;
    private String englishName;
    private String latinName;
    private String description;
//    private String imageUrl;

}
