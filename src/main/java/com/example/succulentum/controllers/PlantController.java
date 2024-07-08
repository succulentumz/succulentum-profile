package com.example.succulentum.controllers;

import com.example.succulentum.models.Plant;
import com.example.succulentum.services.PlantService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class PlantController {
    private static final Logger log = LoggerFactory.getLogger(PlantController.class);
    private final PlantService plantService;

    @GetMapping("/collection")
    public String collection(@RequestParam(name = "englishName", required = false) String englishName, Model model) {
        model.addAttribute("plants", plantService.listPlants(englishName));
        return "collection";
    }

    @GetMapping("/collection/plant/{id}")
    public String plantInfo(@PathVariable Long id, Model model) {
        model.addAttribute("plant", plantService.getPlantById(id));
        return "plant-info";
    }

    @PostMapping("/collection/plant/create")
    public String createPlant(Plant plant) {
        plantService.savePlant(plant);
        log.info("create plant: {}", plant);
        return "redirect:/collection";
    }

    @PostMapping("/collection/plant/delete/{id}")
    public String deletePlant(@PathVariable Long id) {
        plantService.deletePlant(id);
        return "redirect:/collection";
    }

    @PostMapping("/collection/delete")
    public String deleteAllPlants() {
        plantService.deleteAllPlants();
        return "redirect:/collection";
    }

//    @GetMapping("/collection/redir")
//    public String redir(Model model) {
//        return "redirect:/collection";
//    }
}
