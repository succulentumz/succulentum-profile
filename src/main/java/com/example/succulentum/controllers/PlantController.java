package com.example.succulentum.controllers;

import com.example.succulentum.models.Plant;
import com.example.succulentum.services.PlantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class PlantController {
    private final PlantService plantService;

    @GetMapping("/collection")
    public String collection(Model model) {
        model.addAttribute("plants", plantService.getPlantList());
        return "collection";
    }

    @GetMapping("/collection/plant/{id}")
    public String plantInfo(@PathVariable Long id, Model model) {
        model.addAttribute("plant", plantService.getPlantById(id));

        return "plant-info";
    }

    @PostMapping("/collection/plant/create")
    public String createPlant(Plant plant) {
        plantService.addPlant(plant);
        return "redirect:/collection";
    }

    @PostMapping("/collection/plant/delete/{id}")
    public String deletePlant(@PathVariable Long id) {
        plantService.deletePlant(id);
        return "redirect:/collection";
    }

    @GetMapping("/colletion/redir")
    public String redir(Model model) {
        return "redirect:/collection";
    }
}
