package com.erbli.tourplanner.repository;

import com.erbli.tourplanner.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourRepository extends JpaRepository<Tour, String> {

}