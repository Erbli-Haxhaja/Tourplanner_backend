package com.erbli.tourplanner.controllers;
import com.erbli.tourplanner.entities.Tour;
import com.erbli.tourplanner.repositories.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("/tours")
@CrossOrigin(origins = "http://localhost:3000")
public class TourController {
    @Autowired
    private TourRepository tourRepository;

    private static final Logger logger = LogManager.getLogger(TourController.class);

    @PostMapping("/createTour")
    public Tour newTour(@RequestBody Tour newTour) {
        logger.info("Tour Created!");
        return tourRepository.save(newTour);
    }

    @GetMapping
    public List<Tour> getAllTours() {
        logger.info("Tour Returned!");
        return tourRepository.findAll();
    }

    @DeleteMapping("/deleteTour/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        logger.info("Tour with id " + id + " deleted!");
        tourRepository.deleteById(id);
        return "Tour deleted";
    }
}
