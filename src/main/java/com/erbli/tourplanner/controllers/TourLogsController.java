package com.erbli.tourplanner.controllers;

import com.erbli.tourplanner.entities.Tour;
import com.erbli.tourplanner.entities.TourLogs;
import com.erbli.tourplanner.repositories.TourLogRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tourlogs")
@CrossOrigin(origins = "http://localhost:3000")
public class TourLogsController {
    @Autowired
    private TourLogRepository tourlogRepository;

    private static final Logger logger = LogManager.getLogger(TourLogsController.class);

    @PostMapping("/createTourLog")
    public TourLogs newTour(@RequestBody TourLogs newTourLog) {
        logger.info("TourLog Created!");
        return tourlogRepository.save(newTourLog);
    }

    @GetMapping
    public List<TourLogs> getAllTourLogs() {
        logger.info("TourLog Returned!");
        return tourlogRepository.findAll();
    }

    @DeleteMapping("/deleteTourLog/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        logger.info("Tour with id " + id + " deleted!");
        tourlogRepository.deleteById(id);
        return "TourLog deleted";
    }
}
