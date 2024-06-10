package com.erbli.tourplanner.service;

import com.erbli.tourplanner.dtos.TourLogDto;
import com.erbli.tourplanner.entities.TourLogs;
import com.erbli.tourplanner.mapper.TourLogDtoMapper;
import com.erbli.tourplanner.repositories.TourLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourLogService {

    @Autowired
    private TourLogDtoMapper tourLogDtoMapper;

    @Autowired
    private TourLogRepository tourLogsRepository;

    public TourLogDto getTourLogs() {
        TourLogs tourLog = TourLogs.builder()
                .tourid(755)
                .date("12.05.2024")
                .time("12:56")
                .comment("Very good tour")
                .difficulty("Easy")
                .totalDistance("10km")
                .totalTime("1h")
                .rating("5")
                .build();

        return tourLogDtoMapper.mapTourLogToTourLogDto(tourLog);
    }
}
