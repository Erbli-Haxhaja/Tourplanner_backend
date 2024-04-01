package com.erbli.tourplanner.controller;

import com.erbli.tourplanner.model.Tour;
import com.erbli.tourplanner.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TourController {
    @Autowired
    private TourRepository tourRepository;

    @PostMapping("/createTour")
    Tour newTour(@RequestBody Tour newTour) {
        return tourRepository.save(newTour);
    }
}
