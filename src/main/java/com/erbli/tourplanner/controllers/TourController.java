package com.erbli.tourplanner.controllers;

import com.erbli.tourplanner.entities.Tour;
import com.erbli.tourplanner.repositories.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TourController {
    @Autowired
    private TourRepository tourRepository;

    @PostMapping("/createTour")
    Tour newTour(@RequestBody Tour newTour) {
        return tourRepository.save(newTour);
    }

    @GetMapping("/tours")
    List<Tour> getAllTours() {
        return tourRepository.findAll();
    }
}
