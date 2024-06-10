package com.erbli.tourplanner.controllers;
import com.erbli.tourplanner.entities.Tour;
import com.erbli.tourplanner.repositories.TourRepository;
import com.erbli.tourplanner.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

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
        System.out.println(tourRepository.findAll());
        return tourRepository.findAll();
    }

    @DeleteMapping("/deleteTour/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        logger.info("Tour with id " + id + " deleted!");
        tourRepository.deleteById(id);
        return "Tour deleted";
    }

    @GetMapping("/summarizeReport")
    public ResponseEntity<InputStreamResource> generateReport() throws IOException {
        List<Tour> tourList = tourRepository.findAll();
        byte[] pdfBytes = TourService.summarizeReport(tourList);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(pdfBytes);
        InputStreamResource resource = new InputStreamResource(byteArrayInputStream);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=TourReport.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(pdfBytes.length)
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    @GetMapping("/tourReport/{id}")
    public ResponseEntity<InputStreamResource> generateReportForSingleTour(@PathVariable Long id) throws IOException {
        Optional<Tour> tourOptional = tourRepository.findById(id);
        if (!tourOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Tour tour = tourOptional.get();
        byte[] pdfBytes = TourService.tourReport(tour);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(pdfBytes);
        InputStreamResource resource = new InputStreamResource(byteArrayInputStream);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=TourReport_" + tour.getId() + ".pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(pdfBytes.length)
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }
}