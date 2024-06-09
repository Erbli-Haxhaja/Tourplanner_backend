package com.erbli.tourplanner.repositories;

import com.erbli.tourplanner.entities.Tour;
import com.erbli.tourplanner.entities.TourLogs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourLogRepository extends JpaRepository<TourLogs, Long> {

}