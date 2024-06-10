package com.erbli.tourplanner.mapper;

import com.erbli.tourplanner.dtos.TourDto;
import com.erbli.tourplanner.dtos.TourLogDto;
import com.erbli.tourplanner.entities.Tour;
import com.erbli.tourplanner.entities.TourLogs;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TourLogDtoMapper {

    @Autowired
    private ModelMapper modelMapper;

    public TourLogDto mapTourLogToTourLogDto(TourLogs tourlog) {
        TourLogDto tourLogDto = modelMapper.map(tourlog, TourLogDto.class);
        return tourLogDto;
    }

    public TourLogs mapTourLogDtoToTourLog(TourLogDto tourLogDto) {
        TourLogs tourlog = modelMapper.map(tourLogDto, TourLogs.class);
        return tourlog;
    }

}
