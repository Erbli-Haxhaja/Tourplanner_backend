package com.erbli.tourplanner.service;

import com.erbli.tourplanner.dtos.TourDto;
import com.erbli.tourplanner.entities.Tour;
import com.erbli.tourplanner.mapper.TourDtoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TourService {
    @Autowired
    TourDtoMapper tourDtoMapper;
    public TourDto getTours() {
        //Tour tour = new Tour( "Tour1", "This is a tour", "Place 1", "Place 2", "Train", "2km", "2 h", "/usr/photos");
        Tour tour = Tour.builder()
                .name("This is a tour")
                .tourDescription("Description")
                .fromm("From")
                .too("To")
                .transportType("Train")
                .tourDistance("2km")
                .estimatedTime("2h")
                .routeInformation("picture")
                .build();

        TourDto tourDto = tourDtoMapper.mapTourToTourDto(tour);

        return tourDto;
    }
}
