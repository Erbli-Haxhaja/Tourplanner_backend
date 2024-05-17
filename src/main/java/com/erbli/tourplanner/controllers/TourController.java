package com.erbli.tourplanner.controllers;
import com.erbli.tourplanner.entities.Tour;
import com.erbli.tourplanner.repositories.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tours")
@CrossOrigin(origins = "http://localhost:3000")
public class TourController {
    @Autowired
    private TourRepository tourRepository;

    @PostMapping("/createTour")
    public Tour newTour(@RequestBody Tour newTour) {
        return tourRepository.save(newTour);
    }

    @GetMapping
    public List<Tour> getAllTours() {
        return tourRepository.findAll();
    }

    @DeleteMapping("/deleteTour/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        tourRepository.deleteById(id);
        return "Tour deleted";
    }
}
