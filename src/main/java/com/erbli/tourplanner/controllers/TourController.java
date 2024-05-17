package com.erbli.tourplanner.controllers;

import com.erbli.tourplanner.entities.Tour;
import com.erbli.tourplanner.repositories.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tours")
public class TourController {
    @Autowired
    private TourRepository tourRepository;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/createTour")
    public Tour newTour(@RequestBody Tour newTour) {
        return tourRepository.save(newTour);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public List<Tour> getAllTours() {
        return tourRepository.findAll();
    }
}
